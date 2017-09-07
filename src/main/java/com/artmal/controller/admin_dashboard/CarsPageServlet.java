package com.artmal.controller.admin_dashboard;

import com.artmal.controller.LoginServlet;
import com.artmal.model.Car;
import com.artmal.service.CarService;
import com.artmal.service.impl.CarServiceImpl;
import com.artmal.utils.CarUtils;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class CarsPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarService carService = new CarServiceImpl();
        try {
            Set<Car> carSet = carService.findAll();

            req.setAttribute("setOfCars", carSet);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/carsPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CarUtils.addNewCarAsAdmin(req);
        } catch (SQLException e) {
            logger.error(e);
        }

        resp.sendRedirect("/admin-dashboard/cars");
    }
}
