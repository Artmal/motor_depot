package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.users.Dispatcher;
import com.artmal.model.users.Driver;
import com.artmal.service.DispatcherService;
import com.artmal.service.DriverService;
import com.artmal.service.TripService;
import com.artmal.service.impl.DispatcherServiceImpl;
import com.artmal.service.impl.DriverServiceImpl;
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
 * Users can view each others profiles.
 * @author Artem Malchenko
 */
public class ProfilesViewServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ProfilesViewServlet.class);

    private TripService tripService = new TripServiceImpl();
    private DispatcherService dispatcherServiceImpl = new DispatcherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = String.valueOf(req.getRequestURL());

        if(url.contains("drivers")) {
            long driverId = Long.parseLong(req.getParameter("id"));

            DriverService driverServiceImpl = new DriverServiceImpl();
            try {
                Driver driverInfo = driverServiceImpl.findById(driverId);
                req.setAttribute("driver", driverInfo);

                Set<Trip> tripSet = tripService.findAllByDriverId(driverInfo.getId());
                req.setAttribute("setOfTrips", tripSet);
            } catch (SQLException | NamingException | ParseException e) {
                logger.error(e);
            }

            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/driver_profile/driverProfile.jsp")
                    .forward(req, resp);
        } else if(url.contains("dispatchers")) {
            long dispatcherId = Long.parseLong(req.getParameter("id"));

            try {
                Dispatcher dispatcherInfo = dispatcherServiceImpl.findById(dispatcherId);
                req.setAttribute("dispatcher", dispatcherInfo);

                Set<Trip> tripSet = tripService.findAllByDispatcherId(dispatcherId);
                req.setAttribute("setOfCreatedTrips", tripSet);

            } catch (SQLException | NamingException | ParseException e) {
                logger.error(e);
            }

            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/dispatcher_profile/dispatcherProfile.jsp")
                    .forward(req, resp);
        }
    }
}
