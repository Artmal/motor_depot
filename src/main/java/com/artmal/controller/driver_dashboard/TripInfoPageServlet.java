package com.artmal.controller.driver_dashboard;

import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.TripRequest;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.service.DriverService;
import com.artmal.service.TripRequestService;
import com.artmal.service.TripService;
import com.artmal.service.impl.CarServiceImpl;
import com.artmal.service.impl.DriverServiceImpl;
import com.artmal.service.impl.TripRequestServiceImpl;
import com.artmal.service.impl.TripServiceImpl;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
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

    private TripRequestService tripRequestService = new TripRequestServiceImpl();
    private TripService tripService = new TripServiceImpl();
    private CarService carService = new CarServiceImpl();
    private DriverService driverService = new DriverServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tripId = Long.parseLong(req.getParameter("trip-id"));

        try {
            Trip trip = tripService.findById(tripId);
            req.setAttribute("trip", trip);

            Set<TripRequest> tripRequestSet = tripRequestService.findAllByTripId(tripId);
            req.setAttribute("setOfTripRequests", tripRequestSet);

            long userId = (long) req.getSession().getAttribute("id");
            Driver driver = driverService.findByUserId(userId);

            Set<Car> suitableCarSet = carService.findSuitableForTripDriverCars(driver, trip);
            req.setAttribute("setOfSuitableCars", suitableCarSet);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/tripInfoPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long carId = Long.parseLong(req.getParameter("car-id"));
            Car car = carService.findById(carId);

            long tripId = Long.parseLong(req.getParameter("trip-id"));
            Trip trip = tripService.findById(tripId);

            String message = req.getParameter("message");
            TripRequest tripRequest = new TripRequest(trip, car, message);
            tripRequestService.save(tripRequest);

            resp.sendRedirect("/driver-dashboard/trip?trip-id=" + trip.getId());
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }
    }
}