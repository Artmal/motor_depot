package com.artmal.controller;

import com.artmal.model.User;
import com.artmal.model.enums.Role;
import com.artmal.service.UserService;
import com.artmal.service.impl.UserServiceImpl;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println(username);

        UserService userService = new UserServiceImpl();
        try {
            User userInDb = userService.findByUsername(username);

            if(userInDb != null) {
                req.getSession().setAttribute("username", userInDb.getUsername());
                req.getSession().setAttribute("password", userInDb.getPassword());
                req.getSession().setAttribute("role", userInDb.getRole());
            }

            Role role = userInDb.getRole();

            switch(role) {
                case DRIVER:     resp.sendRedirect("/driver-panel");
                                 break;
                case DISPATCHER: resp.sendRedirect("/dispatcher-panel");
                                 break;
                case ADMIN:      resp.sendRedirect("/admin-panel");
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
