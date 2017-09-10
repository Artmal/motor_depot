package com.artmal.controller.admin_driver;

import com.artmal.model.enums.Role;
import com.artmal.service.CarService;
import com.artmal.service.impl.CarServiceImpl;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCarServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(DeleteCarServlet.class);

    private CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long carId = Long.parseLong(req.getParameter("car-id"));
        try {
            carService.deleteById(carId);
        } catch (NamingException | SQLException e) {
            logger.error(e);
        }

        Role role = (Role) req.getSession().getAttribute("role");
        switch (role) {
            case Driver:
                req.getRequestDispatcher("/WEB-INF/views/driver_dashboard/garagePage.jsp").forward(req, resp);
                break;
            case Admin:
                req.getRequestDispatcher("WEB-INF/views/admin_dashboard/carsPage.jsp").forward(req, resp);
        }

    }
}
