package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.enums.CarType;
import com.artmal.model.enums.TripStatus;
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

/**
 * Admin and dispatchers can edit trip info.
 * @author Artem Malchenko
 */
@Log4j
public class EditTripServlet extends HttpServlet {
    @Autowired
    private TripService tripService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tripId = Long.parseLong(req.getParameter("id"));

        try {
            Trip trip = tripService.findById(tripId);
            req.setAttribute("trip", trip);
        } catch (SQLException | NamingException | ParseException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripEditPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tripId = Long.parseLong(req.getParameter("id"));
        TripStatus tripStatus = TripStatus.valueOf(req.getParameter("status"));
        CarType carTypeRequired = CarType.valueOf(req.getParameter("car-type-required"));
        String townFrom = req.getParameter("town-from");
        String townTo = req.getParameter("town-to");
        final DateTime timeOut = TripUtils.stringDateToDateTime(req.getParameter("time-out"));
        final DateTime timeIn = TripUtils.stringDateToDateTime(req.getParameter("time-in"));
        final int paymentInDollars = Integer.parseInt(req.getParameter("payment-in-dollars"));

        Trip updatedTripData = new Trip(tripId, tripStatus, carTypeRequired, townFrom, townTo, timeOut, timeIn, paymentInDollars);
        try {
            tripService.updateTrip(updatedTripData);

            Trip updatedTrip = tripService.findById(tripId);
            req.setAttribute("trip", updatedTrip);
        } catch (SQLException | ParseException | NamingException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/tripInfoPage.jsp").forward(req, resp);
    }
}
