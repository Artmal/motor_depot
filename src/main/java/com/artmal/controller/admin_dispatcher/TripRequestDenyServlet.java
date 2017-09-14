package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.TripRequest;
import com.artmal.model.enums.TripStatus;
import com.artmal.service.TripRequestService;
import com.artmal.service.TripService;
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
import java.text.ParseException;
import java.util.Set;

/**
 * Admin and dispatchers can deny a driver's request.
 * @author Artem Malchenko
 */
@Log4j
public class TripRequestDenyServlet extends HttpServlet {
    @Autowired
    private TripService tripService;
    @Autowired
    private TripRequestService tripRequestService;

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
            Trip trip = tripService.findById(tripId);

            final TripStatus tripStatus = trip.getTripStatus();


            if (tripStatus.equals(TripStatus.Open)) {
                tripRequestService.deleteById(tripRequestId);
            } else if (tripStatus.equals(TripStatus.In_progress)) {
                tripService.nullifyResponsibleCarColumn(trip);
                tripService.setTripStatus(trip, TripStatus.Open);
                tripRequestService.deleteById(tripRequestId);
            }

            trip = tripService.findById(tripId);
            req.setAttribute("trip", trip);

            final Set<TripRequest> tripRequestSet = tripRequestService.findAllByTripId(tripId);
            req.setAttribute("setOfTripRequests", tripRequestSet);
            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripInfoPage.jsp").forward(req, resp);
        } catch (SQLException | NamingException | ParseException e) {
            log.error(e);
        }
    }
}
