package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.enums.CarType;
import com.artmal.model.enums.Role;
import com.artmal.model.enums.TripStatus;
import com.artmal.service.TripService;
import com.artmal.service.impl.DispatcherServiceImpl;
import com.artmal.service.impl.TripServiceImpl;
import com.artmal.utils.TripUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

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
 * Admin and dispatchers can view trips and add new ones.
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

        req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripsPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TripStatus status = TripStatus.valueOf(req.getParameter("status"));
        CarType carTypeRequired = CarType.valueOf(req.getParameter("car-type-required"));
        String townFrom = req.getParameter("town-from");
        String townTo = req.getParameter("town-to");
        DateTime timeOut = TripUtils.stringDateToDateTime(req.getParameter("time-out"));
        DateTime timeIn = TripUtils.stringDateToDateTime(req.getParameter("time-in"));
        int salaryInDollars = Integer.parseInt(req.getParameter("payment-in-dollars"));

        Role role = (Role) req.getSession().getAttribute("role");
        switch (role) {
            case Dispatcher:
                try {
                    long dispatcherId = new DispatcherServiceImpl().findByUserId((long) req.getSession().getAttribute("id")).getId();
                    System.out.println(dispatcherId);

                    Trip trip = new Trip(status, carTypeRequired, townFrom, townTo, timeOut, timeIn, salaryInDollars, dispatcherId);
                    TripService tripService = new TripServiceImpl();
                    tripService.save(trip);
                } catch (SQLException | NamingException | ParseException e) {
                    logger.error(e);
                }

                resp.sendRedirect("/dispatcher-dashboard/trips");
                break;
            case Admin:
                Trip trip = new Trip(status, carTypeRequired, townFrom, townTo, timeOut, timeIn, salaryInDollars);
                TripService tripService = new TripServiceImpl();
                try {
                    tripService.save(trip);
                } catch (SQLException | NamingException | ParseException e) {
                    logger.error(e);
                }

                resp.sendRedirect("/admin-dashboard/trips");
        }
    }
}
