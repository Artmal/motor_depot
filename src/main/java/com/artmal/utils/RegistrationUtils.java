package com.artmal.utils;

import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.model.users.User;
import com.artmal.service.DriverService;
import com.artmal.service.impl.DriverServiceImpl;
import lombok.extern.log4j.Log4j;
import org.mindrot.jbcrypt.BCrypt;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Utility class for registration.
 * @author Artem Malchenko
 */
@Log4j
public final class RegistrationUtils {
    private RegistrationUtils() { }

    /**
     * Separate method for more clear code in servlet part.
     */
    public static void registerNewDriver(HttpServletRequest req) {
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final String fullName = req.getParameter("name");
        final String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        final String phoneNumber = req.getParameter("phone-number");
        final int age = Integer.parseInt(req.getParameter("age"));

        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        final User userInfo = new User(email, hashedPassword, Role.Driver);
        final Driver driver = new Driver(fullName, passportSerialNumbers, phoneNumber, age, userInfo);
        final DriverService driverService = new DriverServiceImpl();
        try {
            driverService.save(driver);
        } catch (SQLException | NamingException e) {
            log.error(e);
        }
    }
}
