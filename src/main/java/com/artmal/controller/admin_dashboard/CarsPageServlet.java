package com.artmal.controller.admin_dashboard;

import com.artmal.model.Car;
import com.artmal.service.CarService;
import com.artmal.utils.CarUtils;
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
import java.util.Set;

/**
 * Admin can view, edit and create new cars in Admin Dashboard.
 * Mapped to: /admin-dashboard/cars
 * @author Artem Malchenko
 */
public class CarsPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(CarsPageServlet.class);

    @Autowired
    private CarService carService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Set<Car> carSet = carService.findAll();

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
