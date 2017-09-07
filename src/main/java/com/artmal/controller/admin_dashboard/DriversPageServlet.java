package com.artmal.controller.admin_dashboard;

import com.artmal.controller.LoginServlet;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.service.impl.DriverServiceImpl;
import com.artmal.utils.RegistrationUtils;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class DriversPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DriverService driverService = new DriverServiceImpl();
        try {
            Set<Driver> driverSet = driverService.findAll();
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
