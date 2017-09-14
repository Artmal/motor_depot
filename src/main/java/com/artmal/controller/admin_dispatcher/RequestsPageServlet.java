package com.artmal.controller.admin_dispatcher;

import com.artmal.model.TripRequest;
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
 * Admin and dispatchers can view pending requests.
 * @author Artem Malchenko
 */
@Log4j
public class RequestsPageServlet extends HttpServlet {
    @Autowired
    TripRequestService tripRequestService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Set<TripRequest> tripRequestSet = tripRequestService.findAllPending();
            req.setAttribute("setOfTripRequests", tripRequestSet);

            req.getRequestDispatcher("/WEB-INF/views/admin_dispatcher/requestsPage.jsp").forward(req, resp);
        } catch (NamingException | SQLException | ParseException e) {
            log.error(e);
        }
    }
}
