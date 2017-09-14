package com.artmal.controller.admin_dispatcher;

import com.artmal.model.Trip;
import com.artmal.model.users.Dispatcher;
import com.artmal.model.users.Driver;
import com.artmal.service.DispatcherService;
import com.artmal.service.DriverService;
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
 * Users can view each others profiles.
 * @author Artem Malchenko
 */
@Log4j
public class ProfilesViewServlet extends HttpServlet {
    @Autowired
    private TripService tripService;
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private DriverService driverService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String url = String.valueOf(req.getRequestURL());

        if (url.contains("drivers")) {
            final long driverId = Long.parseLong(req.getParameter("id"));

            try {
                final Driver driverInfo = driverService.findById(driverId);
                req.setAttribute("driver", driverInfo);

                final Set<Trip> tripSet = tripService.findAllByDriverId(driverInfo.getId());
                req.setAttribute("setOfTrips", tripSet);
            } catch (SQLException | NamingException | ParseException e) {
                log.error(e);
            }

            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/driver_profile/driverProfile.jsp")
                    .forward(req, resp);
        } else if (url.contains("dispatchers")) {
            final long dispatcherId = Long.parseLong(req.getParameter("id"));

            try {
                final Dispatcher dispatcherInfo = dispatcherService.findById(dispatcherId);
                req.setAttribute("dispatcher", dispatcherInfo);

                final Set<Trip> tripSet = tripService.findAllByDispatcherId(dispatcherId);
                req.setAttribute("setOfCreatedTrips", tripSet);

            } catch (SQLException | NamingException | ParseException e) {
                log.error(e);
            }

            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/dispatcher_profile/dispatcherProfile.jsp")
                    .forward(req, resp);
        }
    }
}
