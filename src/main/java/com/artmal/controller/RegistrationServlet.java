package com.artmal.controller;

import com.artmal.model.User;
import com.artmal.model.enums.Role;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.service.UserService;
import com.artmal.service.impl.DriverServiceImpl;
import com.artmal.service.impl.UserServiceImpl;
import com.artmal.utils.RegistrationUtil;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String passportSerialNumber = req.getParameter("passportSerialNumber");
        String phoneNumber = req.getParameter("phone");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        int experience = Integer.parseInt(req.getParameter("experience"));
        Date dateOfBirth = null;
        try {
            dateOfBirth = RegistrationUtil.stringToDate(req.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String password = req.getParameter("password");

        User user = new User(username, password, email, Role.DRIVER);
        UserService userService = new UserServiceImpl();
        try {
            userService.save(user);
            long userId = userService.findByUsername(username).getId();
            Driver driver = new Driver(name, passportSerialNumber, phoneNumber, experience, dateOfBirth, userId);

            DriverService driverService = new DriverServiceImpl();
            driverService.save(driver);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}