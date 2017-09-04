package com.artmal.utils;

import com.artmal.controller.RegistrationServlet;
import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.model.users.User;
import com.artmal.service.DriverService;
import com.artmal.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Utility class for registration.
 * @author Artem Malchenko
 */
public class RegistrationUtils {
    final static Logger logger = Logger.getLogger(RegistrationServlet.class);

    public static void registerNewDriver(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("name");
        String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        String phoneNumber = req.getParameter("phone-number");
        int age = Integer.parseInt(req.getParameter("age"));

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User userInfo = new User(email, hashedPassword, Role.Driver);
        Driver driver = new Driver(fullName, passportSerialNumbers, phoneNumber, age, userInfo);
        DriverService driverService = new DriverServiceImpl();
        try {
            driverService.save(driver);
        } catch (SQLException e) {
            logger.error("Threw a SQLException in RegistrationUtils::registerNewDriver, full stack trace follows:", e);
        } catch (NamingException e) {
            logger.error("Threw a NamingException in RegistrationUtils::registerNewDriver, full stack trace follows:", e);
        }
    }

    public static void registerNewDispatcher(HttpServletRequest req) {

    }
}
