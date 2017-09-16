package com.artmal.controller;

import com.artmal.service.DriverService;
import com.artmal.utils.MailSender;
import com.artmal.utils.VerifyRecaptcha;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
public class ForgotPasswordPageServlet extends HttpServlet {
    @Autowired
    private DriverService driverService;

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

        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if(!verify) {
            req.setAttribute("error", "Did you forget about captcha?");
            req.getRequestDispatcher("/WEB-INF/views/forgotPasswordPage.jsp").forward(req, resp);
        } else {
            String message = "Your new password on site is: ";
            MailSender.sendEmail(email, message);
        }
    }
}
