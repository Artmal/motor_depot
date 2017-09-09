package com.artmal.controller.driver_dashboard;

import com.artmal.model.TripRequest;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.service.TripRequestService;
import com.artmal.service.impl.DriverServiceImpl;
import com.artmal.service.impl.TripRequestServiceImpl;
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
 * Driver can notify dispatcher that he discard current trip for whatever reason.
 * @author Artem Malchenko
 */
public class DiscardTripRequestServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(DiscardTripRequestServlet.class);

    private TripRequestService tripRequestService = new TripRequestServiceImpl();
    private DriverService driverService = new DriverServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tripRequestId = Long.parseLong(req.getParameter("trip-request-id"));

        try {
            tripRequestService.deleteById(tripRequestId);

            long userId = (long) req.getSession().getAttribute("id");
            Driver driver = driverService.findByUserId(userId);
            Set<TripRequest> tripRequestSet = tripRequestService.findAllByDriverId(driver.getId());
            req.setAttribute("setOfTripRequests", tripRequestSet);
        } catch (NamingException | SQLException | ParseException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/myRequestsPage.jsp").forward(req, resp);
    }
}
