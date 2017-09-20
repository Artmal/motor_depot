package com.artmal.controller;

import com.artmal.model.users.User;
import com.artmal.service.UserService;
import com.artmal.utils.MailSender;
import com.artmal.utils.VerifyRecaptcha;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.RandomStringUtils;
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

@Log4j
public class ForgotPasswordPageServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/forgotPasswordPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println(email);

        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if(!verify) {
            Locale locale = (Locale) req.getSession().getAttribute("language");
            if(locale.getLanguage().equals("en")) {
                req.setAttribute("error", "Did you forget about captcha?");
            } else if(locale.getLanguage().equals("ru")) {
                req.setAttribute("error", "Про капчу не забыли?");
            }

            req.getRequestDispatcher("/WEB-INF/views/forgotPasswordPage.jsp").forward(req, resp);
        } else {
            String newPassword = RandomStringUtils.randomAlphabetic(10);
            System.out.println(newPassword);

            try {
                User userInDb = userService.findByEmail(email);

                User userWithNewPass = userInDb;
                final String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

                userWithNewPass.setPassword(hashedPassword);

                userService.updateUser(userWithNewPass);
            } catch (SQLException | NamingException e) {
                log.error(e);
            }

            String message = "Your new password on motor_depot site is: " + newPassword;
            System.out.println(message);
            MailSender.sendEmail(email, message);
            resp.sendRedirect("/login");
        }
    }
}
