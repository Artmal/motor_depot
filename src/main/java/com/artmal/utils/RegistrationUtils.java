package com.artmal.utils;

import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.model.users.User;
import com.artmal.service.DriverService;
import lombok.extern.log4j.Log4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Utility class for registration.
 * @author Artem Malchenko
 */
@Log4j
@Component
public final class RegistrationUtils {
    @Autowired
    private DriverService driverService;

    private RegistrationUtils() { }

    /**
     * Separate method for more clear code in servlet part.
     */
    public void registerNewDriver(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final String fullName = req.getParameter("name");
        final String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        final String phoneNumber = req.getParameter("phone-number");
        final int age = Integer.parseInt(req.getParameter("age"));

        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        final User userInfo = new User(email, hashedPassword, Role.Driver);
        final Driver driver = new Driver(fullName, passportSerialNumbers, phoneNumber, age, userInfo);

        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        System.out.println(gRecaptchaResponse);

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if(!verify) {
            req.setAttribute("error", "How about captcha bruh?");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }

        try {
            driverService.save(driver);
        } catch (SQLException | NamingException e) {
            log.error(e);
        }
    }

    public void registerNewDriverAsAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final String fullName = req.getParameter("full-name");
        final String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        final String phoneNumber = req.getParameter("phone-number");
        final int age = Integer.parseInt(req.getParameter("age"));

        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        final User userInfo = new User(email, hashedPassword, Role.Driver);
        final Driver driver = new Driver(fullName, passportSerialNumbers, phoneNumber, age, userInfo);

        try {
            driverService.save(driver);
        } catch (SQLException | NamingException e) {
            log.error(e);
        }
    }
}
