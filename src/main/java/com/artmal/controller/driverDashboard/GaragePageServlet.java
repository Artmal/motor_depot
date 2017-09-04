package com.artmal.controller.driverDashboard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for garage page in Driver mode.
 * Mapped to: /driver-dashboard/garage
 * @author Artem Malchenko
 */
public class GaragePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String registrationNumber = req.getParameter("registration-number");
        String type = req.getParameter("type");
        String condition = req.getParameter("condition");
        String model = req.getParameter("model");
        String numberOfSeats = req.getParameter("number-of-seats");
        String carColor = req.getParameter("color");
    }
}
