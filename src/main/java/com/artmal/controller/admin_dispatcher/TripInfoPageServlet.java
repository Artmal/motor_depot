package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.TripRequest;
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
 * Servlet for viewing tripInfo for {@link com.artmal.model.users.Administrator}
 * and                              {@link com.artmal.model.users.Dispatcher}
 * Mapped to: /dispatcher-dashboard/trip & /admin-dashboard/trip
 * @author Artem Malchenko
 */
public class TripInfoPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(TripInfoPageServlet.class);

    private TripRequestService tripRequestService = new TripRequestServiceImpl();
    private TripService tripService = new TripServiceImpl();

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

        req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripInfoPage.jsp").forward(req, resp);
    }
}
