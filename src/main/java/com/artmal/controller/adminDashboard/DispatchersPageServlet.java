package com.artmal.controller.adminDashboard;

import com.artmal.controller.LoginServlet;
import com.artmal.model.enums.Role;
import com.artmal.model.users.Dispatcher;
import com.artmal.model.users.User;
import com.artmal.service.DispatcherService;
import com.artmal.service.impl.DispatcherServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Servlet for Dispatchers Page in Admin mode.
 * Mapped to: /Admin-dashboard/dispatcherServlet
 * @author Artem Malchenko
 */
public class DispatchersPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DispatcherService dispatcherService = new DispatcherServiceImpl();
        try {
            Set<Dispatcher> dispatcherSet = dispatcherService.findAll();

            req.setAttribute("setOfDispatchers", dispatcherSet);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/dispatchersPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("name");
        String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        String phoneNumber = req.getParameter("phone-number");
        int salaryInDollars = Integer.parseInt(req.getParameter("salary-in-dollars"));

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User userInfo = new User(email, hashedPassword, Role.Driver);
        Dispatcher dispatcher = new Dispatcher(fullName, passportSerialNumbers, phoneNumber, salaryInDollars, userInfo);
        DispatcherService dispatcherService = new DispatcherServiceImpl();
        try {
            dispatcherService.save(dispatcher);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        resp.sendRedirect("/admin-dashboard/dispatchers");
    }
}
