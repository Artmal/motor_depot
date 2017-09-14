package com.artmal.controller.driver_dashboard;

import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.TripRequest;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.service.DriverService;
import com.artmal.service.TripRequestService;
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
 * Driver can view information about particular trip and make request if possible.
 * Mapped to: /driver-dashboard/trip
 * @author Artem Malchenko
 */
public class TripInfoPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(TripInfoPageServlet.class);

    @Autowired
    private TripRequestService tripRequestService;
    @Autowired
    private TripService tripService;
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
        final long tripId = Long.parseLong(req.getParameter("trip-id"));

        try {
            final Trip trip = tripService.findById(tripId);
            req.setAttribute("trip", trip);

            final Set<TripRequest> tripRequestSet = tripRequestService.findAllByTripId(tripId);
            req.setAttribute("setOfTripRequests", tripRequestSet);

            final long userId = (long) req.getSession().getAttribute("id");
            final Driver driver = driverService.findByUserId(userId);

            final Set<Car> suitableCarSet = carService.findSuitableForTripDriverCars(driver, trip);
            req.setAttribute("setOfSuitableCars", suitableCarSet);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/tripInfoPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final long carId = Long.parseLong(req.getParameter("car-id"));
            final Car car = carService.findById(carId);

            final long tripId = Long.parseLong(req.getParameter("trip-id"));
            final Trip trip = tripService.findById(tripId);

            final String message = req.getParameter("message");
            final TripRequest tripRequest = new TripRequest(trip, car, message);
            tripRequestService.save(tripRequest);

            resp.sendRedirect("/driver-dashboard/trip?trip-id=" + trip.getId());
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }
    }
}