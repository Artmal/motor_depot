package com.artmal.controller.admin_dashboard;

import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.utils.RegistrationUtils;
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
import java.util.Set;

/**
 * Admin can delete, edit information for drivers and create new ones.
 * Mapped to: /admin-dashboard/drivers
 * @author Artem Malchenko
 */
public class DriversPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(DriversPageServlet.class);

    @Autowired
    private DriverService driverService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Set<Driver> driverSet = driverService.findAll();
            req.setAttribute("setOfDrivers", driverSet);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/driversPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationUtils.registerNewDriver(req);
        resp.sendRedirect("/admin-dashboard/drivers");
    }
}
