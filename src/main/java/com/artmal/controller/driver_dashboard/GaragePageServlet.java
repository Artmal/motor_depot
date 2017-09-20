package com.artmal.controller.driver_dashboard;

import com.artmal.model.Car;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.service.DriverService;
import com.artmal.utils.CarUtils;
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
 * Driver can view, edit and add new cars in their garages.
 * Mapped to: /driver-dashboard/garageServlet
 * @author Artem Malchenko
 */
@Log4j
public class GaragePageServlet extends HttpServlet {
    @Autowired
    private DriverService driverService;
    @Autowired
    private CarService carService;
    @Autowired
    private CarUtils carUtils;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Driver loggedDriver = driverService.findByUserId((Long) req.getSession().getAttribute("id"));

            final long ownerId = loggedDriver.getId();

            final Set<Car> carSet = carService.findAllByOwnerId(ownerId);
            req.setAttribute("setOfCars", carSet);
        } catch (SQLException | NamingException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/garagePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            carUtils.addNewCarAsDriver(req);
        } catch (SQLException | NamingException e) {
            log.error(e);
        }

        resp.sendRedirect("/driver-dashboard/garage");
    }
}
