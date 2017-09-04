package com.artmal.controller;

import com.artmal.model.enums.Role;
import com.artmal.model.users.Administrator;
import com.artmal.model.users.User;
import com.artmal.service.AdministratorService;
import com.artmal.service.UserService;
import com.artmal.service.impl.AdministratorServiceImpl;
import com.artmal.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MyProfilePageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(MyProfilePageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = (String) req.getSession().getAttribute("email");

        UserService userService = new UserServiceImpl();
        try {
            User loggedUser = userService.findByEmail(userEmail);

            Role role = loggedUser.getRole();

            switch(role) {
                case Driver:
                    req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/myProfilePage.jsp").forward(req, resp);
                    break;
                case Dispatcher: req.getRequestDispatcher("/WEB-INF/views/dispatcher_dashboard/myProfilePage.jsp").forward(req, resp);
                    break;
                case Admin:
                    AdministratorService administratorService = new AdministratorServiceImpl();
                    Administrator admin = administratorService.findByUserId(loggedUser.getId());
                    req.getSession().setAttribute("name", admin.getName());
                    req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/myProfilePage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
