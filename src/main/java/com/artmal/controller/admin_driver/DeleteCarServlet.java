package com.artmal.controller.admin_driver;

import com.artmal.model.Car;
import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.service.DriverService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Admin and drivers can delete cars.
 * @author Artem Malchenko
 */
public class DeleteCarServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(DeleteCarServlet.class);

    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long carId = Long.parseLong(req.getParameter("car-id"));
        try {
            carService.deleteById(carId);

            Role role = (Role) req.getSession().getAttribute("role");
            switch (role) {
                case Driver:
                    Driver loggedDriver = driverService.findByUserId((Long) req.getSession().getAttribute("id"));
                    long ownerId = loggedDriver.getId();

                    Set<Car> carSet = carService.findAllByOwnerId(ownerId);
                    req.setAttribute("setOfCars", carSet);

                    req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/garagePage.jsp").forward(req, resp);
                    break;
                case Admin:
                    Set<Car> carSetForAdmin = carService.findAll();
                    req.setAttribute("setOfCars", carSetForAdmin);

                    req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/carsPage.jsp").forward(req, resp);
            }

        } catch (NamingException | SQLException e) {
            logger.error(e);
        }
    }
}
