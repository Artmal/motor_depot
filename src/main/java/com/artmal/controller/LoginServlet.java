package com.artmal.controller;

import com.artmal.model.User;
import com.artmal.model.enums.Role;
import com.artmal.service.UserService;
import com.artmal.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet for login page..
 * Mapped to: /loginServlet
 * @author Artem Malchenkon
 */
public class LoginServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("email");

        UserService userService = new UserServiceImpl();
        try {
            User userInDb = userService.findByEmail(username);

            if(BCrypt.checkpw(req.getParameter("password"), userInDb.getPassword())) {
                req.getSession().setAttribute("username", userInDb.getEmail());
                req.getSession().setAttribute("password", userInDb.getPassword());
                req.getSession().setAttribute("role", userInDb.getRole());
            }

            Role role = userInDb.getRole();

            switch(role) {
                case DRIVER:     resp.sendRedirect("/driver-panel");
                                 break;
                case DISPATCHER: resp.sendRedirect("/dispatcher-panel");
                                 break;
                case ADMIN:      resp.sendRedirect("/admin-dashboard");
            }
        } catch (SQLException e) {
            logger.error("Threw a SQLException in LoginServlet::doPost, full stack trace follows:", e);
        } catch (NamingException e) {
            logger.error("Threw a NamingException in LoginServlet::doPost, full stack trace follows:", e);
        }
    }
}
