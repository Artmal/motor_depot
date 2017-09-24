package com.artmal.controller;

import com.artmal.model.users.User;
import com.artmal.service.UserService;
import com.artmal.utils.ValidationException;
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

/**
 * Users can change information about their profiles(email and password).
 * @author Artem Malchenko
 */
@Log4j
public class MySettingsPageServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String email = (String) req.getSession().getAttribute("email");
        try {
            final User user = userService.findByEmail(email);
            req.setAttribute("user", user);

        } catch (SQLException | NamingException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/settingsPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final User user = new User();
        final long userId = (long) req.getSession().getAttribute("id");
        final String newEmail = req.getParameter("email");
        final String password = req.getParameter("password");
        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        user.setId(userId);
        user.setEmail(newEmail);
        user.setPassword(hashedPassword);
        try {
            userService.updateUser(user);
        } catch (SQLException | NamingException | ValidationException e) {
            log.error(e);
        }

        req.getSession().invalidate();
        resp.sendRedirect("/login");
    }
}
