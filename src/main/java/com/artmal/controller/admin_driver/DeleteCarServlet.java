package com.artmal.controller.admin_driver;

import com.artmal.model.Car;
import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.service.DriverService;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class DeleteCarServlet extends HttpServlet {
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
        final long carId = Long.parseLong(req.getParameter("car-id"));
        try {
            carService.deleteById(carId);

            final Role role = (Role) req.getSession().getAttribute("role");
            if (role.equals(Role.Driver)) {
                final Driver loggedDriver = driverService.findByUserId((Long) req.getSession().getAttribute("id"));
                final long ownerId = loggedDriver.getId();

                final Set<Car> carSet = carService.findAllByOwnerId(ownerId);
                req.setAttribute("setOfCars", carSet);

                req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/garagePage.jsp").forward(req, resp);
            } else if (role.equals(Role.Admin)) {
                final Set<Car> carSetForAdmin = carService.findAll();
                req.setAttribute("setOfCars", carSetForAdmin);

                req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/carsPage.jsp").forward(req, resp);
            }
        } catch (NamingException | SQLException e) {
            log.error(e);
        }
    }
}
