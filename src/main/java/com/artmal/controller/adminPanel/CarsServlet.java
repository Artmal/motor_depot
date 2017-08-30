package com.artmal.controller.adminPanel;

import com.artmal.dao.impl.CarDaoImpl;
import com.artmal.model.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CarsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars = null;

        try {
            cars = new CarDaoImpl().findAllCars();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(cars != null) {
            req.setAttribute("listOfCars", cars);
            req.getRequestDispatcher("/WEB-INF/views/admin_panel/cars-tab.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
