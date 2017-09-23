package com.artmal.controller.admin_dashboard;

import com.artmal.model.Car;
import com.artmal.service.CarService;
import com.artmal.service.TripService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log4j
public class TaskServlet extends HttpServlet {
    @Autowired
    private CarService carService;

    @Autowired
    private TripService tripService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Set<Car> carSet = carService.findAll();

            Map<Long, Long> result = new HashMap<>();
            for(Car car : carSet) {
                long amountOfTrips = tripService.findAllByCarId(car.getId()).size();
                result.put(car.getId(), amountOfTrips);
            }

            req.setAttribute("resultMap", result);
        } catch (SQLException | NamingException | ParseException e) {
            log.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/task.jsp").forward(req, resp);

    }
}
