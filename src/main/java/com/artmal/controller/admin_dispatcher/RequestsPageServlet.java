package com.artmal.controller.admin_dispatcher;

import com.artmal.model.TripRequest;
import com.artmal.service.TripRequestService;
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
 * Admin and dispatchers can view pending requests.
 * @author Artem Malchenko
 */
public class RequestsPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(RequestsPageServlet.class);

    private TripRequestService tripRequestService = new TripRequestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Set<TripRequest> tripRequestSet = tripRequestService.findAllPending();
            req.setAttribute("setOfTripRequests", tripRequestSet);

            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/requestsPage.jsp").forward(req, resp);
        } catch (NamingException | SQLException | ParseException e) {
            logger.error(e);
        }
    }
}