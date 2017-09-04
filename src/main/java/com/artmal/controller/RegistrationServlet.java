package com.artmal.controller;

import com.artmal.utils.RegistrationUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling registration process.
 * Registration available only for drivers.
 * Get parameter from reg page -> create bean -> save to db.
 * Mapped to: /register
 * @author Artem Malchenko
 */
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationUtils.registerNewDriver(req);
        resp.sendRedirect("/login");
    }
}