package com.artmal.controller;

import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.TripRequest;
import com.artmal.model.enums.Role;
import com.artmal.service.CarService;
import com.artmal.service.TripRequestService;
import com.artmal.service.TripService;
import com.artmal.service.impl.CarServiceImpl;
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

public class TripInfoPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(TripInfoPageServlet.class);


    private TripRequestService tripRequestService = new TripRequestServiceImpl();
    private TripService tripService = new TripServiceImpl();
    private CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tripId = Long.parseLong(req.getParameter("trip-id"));

        try {
            Trip trip = tripService.findById(tripId);
            req.setAttribute("trip", trip);

            Set<TripRequest> tripRequestSet = tripRequestService.findAllByTripId(tripId);
            req.setAttribute("setOfTripRequests", tripRequestSet);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }

        Role role = (Role) req.getSession().getAttribute("role");
        switch(role) {
            case Driver:
                req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/tripInfoPage.jsp").forward(req, resp);
                return;
            case Dispatcher:
                req.getRequestDispatcher("/WEB-INF/views/dispatcher_dashboard/tripInfoPage.jsp").forward(req, resp);
                return;
            case Admin:
                req.getRequestDispatcher("/WEB-INF/views/administrator_dashboard/tripInfoPage.jsp").forward(req, resp);
        }
    }

    /**
     * Only drivers can invoke the doPost(). Others don't have the form.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getAttribute("role").equals("Driver")) {
            resp.sendError(403);
        }

        try {
            long carId = Long.parseLong(req.getParameter("car-id"));
            Car car = carService.findById(carId);

            long tripId = Long.parseLong(req.getParameter("trip-id"));
            Trip trip = tripService.findById(tripId);

            String message = req.getParameter("message");
            TripRequest tripRequest = new TripRequest(trip, car, message);
            tripRequestService.save(tripRequest);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }
    }
}