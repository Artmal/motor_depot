package com.artmal.controller;

import com.artmal.model.User;
import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.service.UserService;
import com.artmal.service.impl.DriverServiceImpl;
import com.artmal.service.impl.UserServiceImpl;
import com.artmal.utils.RegistrationUtil;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * Servlet for handling registration process.
 * Get parameter from reg page -> create bean -> save to db.
 * Mapped to: /register
 * @author Artem Malchenko
 */
public class RegistrationServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String passportSerialNumber = req.getParameter("passportSerialNumber");
        String phoneNumber = req.getParameter("phone");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        int experience = Integer.parseInt(req.getParameter("experience"));
        Date dateOfBirth = null;
        try {
            dateOfBirth = RegistrationUtil.stringToDate(req.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            logger.error("Threw a ParseException in RegistrationServlet::doPost, full stack trace follows:", e);
        }
        String password = req.getParameter("password");

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(email, hashedPassword, Role.DRIVER);
        UserService userService = new UserServiceImpl();
        try {
            userService.save(user);
            long userId = userService.findByEmail(username).getId();
            Driver driver = new Driver(name, passportSerialNumber, phoneNumber, experience, dateOfBirth, userId);

            DriverService driverService = new DriverServiceImpl();
            driverService.save(driver);
        } catch (SQLException e) {
            logger.error("Threw a SQLException in RegistrationServlet::doPost, full stack trace follows:", e);
        } catch (NamingException e) {
            logger.error("Threw a NamingException in RegistrationServlet::doPost, full stack trace follows:", e);
        }
    }
}