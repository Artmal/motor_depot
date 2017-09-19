package com.artmal.controller.admin_driver;

import com.artmal.model.Car;
import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;
import com.artmal.model.enums.Role;
import com.artmal.service.CarService;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class CarInfoPageServlet extends HttpServlet {
    @Autowired
    private CarService carService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long carId = Long.parseLong(req.getParameter("id"));

        try {
            final Car car = carService.findById(carId);

            req.setAttribute("carInfo", car);
        } catch (SQLException | NamingException | ParseException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_driver/carInfoPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long carId = Long.parseLong(req.getParameter("id"));
        final String registrationNumber = req.getParameter("registration-number");
        final CarType type = CarType.valueOf(req.getParameter("type"));
        final CarCondition condition = CarCondition.valueOf(req.getParameter("condition"));
        final String model = req.getParameter("model");
        final int numberOfSeats = Integer.parseInt(req.getParameter("number-of-seats"));
        final String color = req.getParameter("color");

        final Car car = new Car(carId, registrationNumber, type, condition, model, numberOfSeats, color);
        try {
            carService.updateCar(car);
        } catch (NamingException | SQLException e) {
            log.error(e);
        }

        final Role role = (Role) req.getSession().getAttribute("role");

        if (role.equals(Role.Driver)) {
            resp.sendRedirect("/driver-dashboard/garage");
        } else if (role.equals(Role.Admin)) {
            resp.sendRedirect("/admin-dashboard/cars");
        }
    }
}
