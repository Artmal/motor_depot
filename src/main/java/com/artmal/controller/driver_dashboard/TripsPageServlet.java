package com.artmal.controller.driver_dashboard;

import com.artmal.model.Trip;
import com.artmal.service.TripService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
 * Driver can view list of trips.
 * Mapped to: /driver-dashboard/trips
 * @author Artem Malchenko
 */
@Log4j
public class TripsPageServlet extends HttpServlet {
    @Autowired
    private TripService tripService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Set<Trip> tripSet = tripService.findAllOpen();
            req.setAttribute("setOfTrips", tripSet);
        } catch (SQLException | ParseException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/tripsPage.jsp").forward(req, resp);
    }
}
