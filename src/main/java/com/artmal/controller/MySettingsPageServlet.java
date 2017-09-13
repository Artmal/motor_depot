package com.artmal.controller;

import com.artmal.model.users.User;
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

public class MySettingsPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(MySettingsPageServlet.class);

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        try {
            User user = userService.findByEmail(email);
            req.setAttribute("user", user);

        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/settingsPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        long userId = (long) req.getSession().getAttribute("id");
        String newEmail = req.getParameter("email");
        String password = req.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        user.setId(userId);
        user.setEmail(newEmail);
        user.setPassword(hashedPassword);
        try {
            userService.updateUser(user);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        req.getSession().invalidate();
        resp.sendRedirect("/login");
    }
}
