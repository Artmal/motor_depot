package com.artmal.controller.admin_dispatcher.driver_profile;

import com.artmal.model.Car;
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
 * Driver's profile contains garage page which can be viewed by admin.
 * @author Artem Malchenko
 */
@Log4j
public class DriverGaragePageServlet extends HttpServlet {
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
        final long driverId = Long.parseLong(req.getParameter("driver-id"));

        try {
            final Set<Car> carSet = carService.findAllByOwnerId(driverId);
            req.setAttribute("setOfCars", carSet);

            final Driver driver = driverService.findById(driverId);
            req.setAttribute("driver", driver);
        } catch (SQLException | NamingException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/driver_profile/driverGaragePage.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
