package com.artmal.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phone");
        String email = req.getParameter("email");
        String car = req.getParameter("car");
        int experience = Integer.parseInt(req.getParameter("experience"));
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
    }
}
