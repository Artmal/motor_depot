package com.artmal.controller.driver_dashboard;

import com.artmal.model.TripRequest;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.service.TripRequestService;
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
 * Driver can notify dispatcher that he discard current trip for whatever reason.
 * @author Artem Malchenko
 */
@Log4j
public class DiscardTripRequestServlet extends HttpServlet {
    @Autowired
    private TripRequestService tripRequestService;
    @Autowired
    private DriverService driverService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long tripRequestId = Long.parseLong(req.getParameter("trip-request-id"));

        try {
            tripRequestService.deleteById(tripRequestId);

            final long userId = (long) req.getSession().getAttribute("id");
            final Driver driver = driverService.findByUserId(userId);
            final Set<TripRequest> tripRequestSet = tripRequestService.findAllByDriverId(driver.getId());
            req.setAttribute("setOfTripRequests", tripRequestSet);
        } catch (NamingException | SQLException | ParseException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/myRequestsPage.jsp").forward(req, resp);
    }
}
