package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.TripRequest;
import com.artmal.model.enums.TripStatus;
import com.artmal.service.TripRequestService;
import com.artmal.service.TripService;
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
 * Admin and dispatchers can deny a driver's request.
 * @author Artem Malchenko
 */
public class TripRequestDenyServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(TripRequestDenyServlet.class);

    private TripService tripService = new TripServiceImpl();
    private TripRequestService tripRequestService = new TripRequestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tripId = Long.parseLong(req.getParameter("trip-id"));
        long tripRequestId = Long.parseLong(req.getParameter("trip-request-id"));

        try {
            Trip trip = tripService.findById(tripId);

            TripStatus tripStatus = trip.getTripStatus();

            switch (tripStatus) {
                case Open:
                    tripRequestService.deleteById(tripRequestId);
                    break;
                case In_progress:
                    tripService.nullifyResponsibleCarColumn(trip);
                    tripService.setTripStatus(trip, TripStatus.Open);
                    tripRequestService.deleteById(tripRequestId);
            }

            trip = tripService.findById(tripId);
            req.setAttribute("trip", trip);

            Set<TripRequest> tripRequestSet = tripRequestService.findAllByTripId(tripId);
            req.setAttribute("setOfTripRequests", tripRequestSet);
            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripInfoPage.jsp").forward(req, resp);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }
    }
}
