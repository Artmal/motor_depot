package com.artmal.controller.driver_dashboard;

import com.artmal.model.Trip;
import com.artmal.service.TripService;
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
 * Driver can view list of trips.
 * Mapped to: /driver-dashboard/trips
 * @author Artem Malchenko
 */
public class TripsPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(TripsPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TripService tripService = new TripServiceImpl();
        try {
            Set<Trip> tripSet = tripService.findAll();
            req.setAttribute("setOfTrips", tripSet);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/tripsPage.jsp").forward(req, resp);
    }
}
