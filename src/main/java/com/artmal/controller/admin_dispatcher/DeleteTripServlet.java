package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
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
 * Admin and dispatchers can delete trips.
 * @author Artem Malchenko
 */
@Log4j
public class DeleteTripServlet extends HttpServlet {
    @Autowired
    private TripService tripService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long tripId = Long.parseLong(req.getParameter("id"));

        try {
            tripService.deleteById(tripId);
            final Set<Trip> tripSet = tripService.findAll();
            req.setAttribute("setOfTrips", tripSet);
        } catch (NamingException | SQLException | ParseException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripsPage.jsp").forward(req, resp);
    }
}
