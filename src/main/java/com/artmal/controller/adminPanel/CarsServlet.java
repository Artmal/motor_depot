package com.artmal.controller.adminPanel;

import com.artmal.dao.impl.CarDaoImpl;
import com.artmal.model.Car;
import com.artmal.service.CarService;
import com.artmal.service.impl.CarServiceImpl;

import javax.naming.NamingException;
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
        Car car = new Car();

        car.setRegistrationNumber(req.getParameter("addCar_regNumber"));
        car.setCarType(req.getParameter("addCar_carType"));
        car.setManufacturer(req.getParameter("addCar_manufacturer"));
        car.setModel(req.getParameter("addCar_model"));
        car.setProductionYear(Integer.parseInt(req.getParameter("addCar_productionYear")));
        car.setNumberOfSeats(Integer.parseInt(req.getParameter("addCar_numberOfSeats")));
        car.setColor(req.getParameter("addCar_color"));
        car.setMileage(Integer.parseInt(req.getParameter("addCar_mileage")));
        car.setCarCondition(req.getParameter("addCar_condition"));

        CarService carService = new CarServiceImpl();
        try {
            carService.save(car);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

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
}