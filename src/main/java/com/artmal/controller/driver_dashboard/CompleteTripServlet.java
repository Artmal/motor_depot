package com.artmal.controller.driver_dashboard;

import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.service.TripService;
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
import java.text.ParseException;
import java.util.Set;

/**
 * Driver can notify dispatcher that he successfully completed particular trip.
 * @author Artem Malchenko
 */
public class CompleteTripServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(CompleteTripServlet.class);

    @Autowired
    private TripService tripService;
    @Autowired
    private DriverService driverService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tripId = Long.parseLong(req.getParameter("trip-id"));

        try {
            Trip trip = tripService.findById(tripId);
            tripService.setTripStatus(trip, TripStatus.Closed);

            long userId = (long) req.getSession().getAttribute("id");
            Driver driver = driverService.findByUserId(userId);

            Set<Trip> tripSet = tripService.findAllByDriverId(driver.getId());
            req.setAttribute("setOfTrips", tripSet);

            req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/myTripsPage.jsp").forward(req, resp);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/myTripsPage.jsp");
    }
}
