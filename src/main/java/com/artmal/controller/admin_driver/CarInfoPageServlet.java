package com.artmal.controller.admin_driver;

import com.artmal.model.Car;
import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;
import com.artmal.model.enums.Role;
import com.artmal.service.CarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Admin and drivers can change information about cars.
 * @author Artem Malchenko
 */
public class CarInfoPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(CarInfoPageServlet.class);

    @Autowired
    private CarService carService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long carId = Long.parseLong(req.getParameter("id"));

        try {
            Car car = carService.findById(carId);

            req.setAttribute("carInfo", car);
        } catch (SQLException | NamingException | ParseException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("WEB-INF/views/admin_driver/carInfoPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long carId = Long.parseLong(req.getParameter("id"));
        String registrationNumber = req.getParameter("registration-number");
        CarType type = CarType.valueOf(req.getParameter("type"));
        CarCondition condition = CarCondition.valueOf(req.getParameter("condition"));
        String model = req.getParameter("model");
        int numberOfSeats = Integer.parseInt(req.getParameter("number-of-seats"));
        String color = req.getParameter("color");

        Car car = new Car(carId, registrationNumber, type, condition, model, numberOfSeats, color);
        try {
            carService.updateCar(car);
        } catch (NamingException | SQLException e) {
            logger.error(e);
        }

        Role role = (Role) req.getSession().getAttribute("role");
        switch (role) {
            case Driver:
                resp.sendRedirect("/driver-dashboard/garage");
                break;
            case Admin:
                resp.sendRedirect("/admin-dashboard/cars");
        }
    }
}
