package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.TripRequest;
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
 * Admin and dispatchers can accept driver's request for a trip.
 * Mapped to: /dispatcher-dashboard/trip/accept
 * @author Artem Malchenko
 */
public class TripRequestAcceptServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(TripRequestAcceptServlet.class);

    @Autowired
    private TripRequestService tripRequestService;
    @Autowired
    private TripService tripService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long tripId = Long.parseLong(req.getParameter("trip-id"));
        final long tripRequestId = Long.parseLong(req.getParameter("trip-request-id"));

        try {
            final TripRequest tripRequest = tripRequestService.findById(tripRequestId);
            final Car car = tripRequest.getCarInfo();

            Trip trip = tripService.findById(tripId);
            tripService.assignCarToTheTrip(trip, car);

            //refresh
            trip = tripService.findById(tripId);
            req.setAttribute("trip", trip);

            final Set<TripRequest> tripRequestSet = tripRequestService.findAllByTripId(tripId);
            req.setAttribute("setOfTripRequests", tripRequestSet);

            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripInfoPage.jsp").forward(req, resp);
        } catch (NamingException | SQLException | ParseException e) {
            logger.error(e);
        }
    }
}
