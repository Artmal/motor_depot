package com.artmal.controller.driver_dashboard;

import com.artmal.model.TripRequest;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.service.TripRequestService;
import org.apache.log4j.Logger;
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
 * Driver can view his/her requests for trips.
 * Mapped to: /driver-dashboard/my-requests
 * @author Artem Malchenko
 */

public class MyTripRequestsPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(MyTripRequestsPageServlet.class);

    @Autowired
    private DriverService driverService;
    @Autowired
    private TripRequestService tripRequestService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long userId = (long) req.getSession().getAttribute("id");
        try {
            final Driver driver = driverService.findByUserId(userId);

            final Set<TripRequest> tripRequestSet = tripRequestService.findAllByDriverId(driver.getId());
            req.setAttribute("setOfTripRequests", tripRequestSet);

            req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/myRequestsPage.jsp").forward(req, resp);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }
    }
}
