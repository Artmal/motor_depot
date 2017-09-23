package com.artmal.controller.admin_dashboard;

import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
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
public class HardTaskServlet extends HttpServlet {
    @Autowired
    private TripService tripService;

    @Autowired
    private DriverService driverService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Long, Long> result = new HashMap<>();

        try {
            Set<Driver> driverSet = driverService.findAll();
            for(Driver driver : driverSet) {
                long amountOfTrips = tripService.findAllByDriverId(driver.getId()).size();

                Long driverId = driver.getId();
                result.put(driverId, amountOfTrips);
            }
        } catch (SQLException | NamingException | ParseException e) {
            log.error(e);
        }

        req.setAttribute("resultMap", result);
        req.getRequestDispatcher("/WEB-INF/views/hardTask.jsp").forward(req, resp);
    }
}
