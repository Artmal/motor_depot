package com.artmal.controller.adminDashboard;

import com.artmal.controller.LoginServlet;
import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.model.users.User;
import com.artmal.service.DriverService;
import com.artmal.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DriversPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("name");
        String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        String phoneNumber = req.getParameter("phone-number");

        int age = Integer.parseInt(req.getParameter("age"));

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User userInfo = new User(email, hashedPassword, Role.DRIVER);
        Driver driver = new Driver(fullName, passportSerialNumbers, phoneNumber, age, userInfo);
        DriverService driverService = new DriverServiceImpl();
        try {
            driverService.save(driver);
        } catch (SQLException e) {
            logger.error("Threw a SQLException in RegistrationServlet::doPost, full stack trace follows:", e);
        } catch (NamingException e) {
            logger.error("Threw a NamingException in RegistrationServlet::doPost, full stack trace follows:", e);
        }

        resp.sendRedirect("/admin-dashboard/drivers");

    }
}
