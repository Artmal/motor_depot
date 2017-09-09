package com.artmal.controller.admin_dashboard;

import com.artmal.model.Car;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.service.DriverService;
import com.artmal.service.impl.CarServiceImpl;
import com.artmal.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Driver's profile contains garage page which can be viewed by admin.
 * @author Artem Malchenko
 */
public class DriverGaragePageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(DriverGaragePageServlet.class);

    private CarService carService = new CarServiceImpl();
    private DriverService driverService = new DriverServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long driverId = Long.parseLong(req.getParameter("driver-id"));

        try {
            Set<Car> carSet = carService.findAllByOwnerId(driverId);
            req.setAttribute("setOfCars", carSet);

            Driver driver = driverService.findById(driverId);
            req.setAttribute("driver", driver);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/profiles_view/driver/driverGaragePage.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
