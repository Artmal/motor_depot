package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.enums.CarType;
import com.artmal.model.enums.Role;
import com.artmal.model.enums.TripStatus;
import com.artmal.service.DispatcherService;
import com.artmal.service.TripService;
import com.artmal.utils.TripUtils;
import lombok.extern.log4j.Log4j;
import org.joda.time.DateTime;
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
 * Admin and dispatchers can view trips and add new ones.
 * @author Artem Malchenko
 */
@Log4j
public class TripsPageServlet extends HttpServlet {
    @Autowired
    private TripService tripService;
    @Autowired
    private DispatcherService dispatcherService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Set<Trip> tripSet = tripService.findAll();
            req.setAttribute("setOfTrips", tripSet);
        } catch (SQLException | NamingException | ParseException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripsPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final TripStatus status = TripStatus.valueOf(req.getParameter("status"));
        final CarType carTypeRequired = CarType.valueOf(req.getParameter("car-type-required"));
        final String townFrom = req.getParameter("town-from");
        final String townTo = req.getParameter("town-to");
        final DateTime timeOut = TripUtils.stringDateToDateTime(req.getParameter("time-out"));
        final DateTime timeIn = TripUtils.stringDateToDateTime(req.getParameter("time-in"));
        final int salaryInDollars = Integer.parseInt(req.getParameter("payment-in-dollars"));

        final Role role = (Role) req.getSession().getAttribute("role");
        if (role.equals(Role.Dispatcher)) {
            try {
                final long dispatcherId = dispatcherService.findByUserId((long) req.getSession().getAttribute("id")).getId();

                final Trip trip = new Trip(status, carTypeRequired, townFrom, townTo, timeOut, timeIn, salaryInDollars, dispatcherId);
                tripService.save(trip);
            } catch (SQLException | NamingException | ParseException e) {
                log.error(e);
            }

            resp.sendRedirect("/dispatcher-dashboard/trips");
        } else if (role.equals(Role.Admin)) {
            final Trip trip = new Trip(status, carTypeRequired, townFrom, townTo, timeOut, timeIn, salaryInDollars);
            try {
                tripService.save(trip);
            } catch (SQLException | NamingException | ParseException e) {
                log.error(e);
            }

            resp.sendRedirect("/admin-dashboard/trips");
        }
    }
}
