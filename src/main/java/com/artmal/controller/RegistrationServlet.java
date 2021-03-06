package com.artmal.controller;

import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.model.users.User;
import com.artmal.service.DriverService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.VerifyRecaptcha;
import lombok.extern.log4j.Log4j;
import org.mindrot.jbcrypt.BCrypt;
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
import java.util.Locale;

/**
 * Servlet for handling registration process.
 * Registration available only for drivers.
 * Get parameter from reg page -> create bean -> save to db.
 * Mapped to: /register
 * @author Artem Malchenko
 */
@Log4j
public class RegistrationServlet extends HttpServlet {
    @Autowired
    private DriverService driverService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final String fullName = req.getParameter("full-name");
        final String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        final String phoneNumber = req.getParameter("phone-number");
        final int age = Integer.parseInt(req.getParameter("age"));

        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        final User userInfo = new User(email, hashedPassword, Role.Driver);
        final Driver driver = new Driver(fullName, passportSerialNumbers, phoneNumber, age, userInfo);

        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if(!verify) {
            Locale locale = (Locale) req.getSession().getAttribute("language");
            if(locale.getLanguage().equals("en")) {
                req.setAttribute("error", "Did you forget about captcha?");
            } else if(locale.getLanguage().equals("ru")) {
                req.setAttribute("error", "Про капчу не забыли?");
            }

            req.setAttribute("userInfo", userInfo);
            req.setAttribute("driverInfo", driver);
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        } else {
            try {
                driverService.save(driver);
            } catch (SQLException | NamingException | ValidationException e) {
                log.error(e);
            }
        }

        resp.sendRedirect("/login");
    }
}
