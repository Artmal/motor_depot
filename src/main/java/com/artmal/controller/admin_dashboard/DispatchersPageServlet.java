package com.artmal.controller.admin_dashboard;

import com.artmal.model.enums.Role;
import com.artmal.model.users.Dispatcher;
import com.artmal.model.users.User;
import com.artmal.service.DispatcherService;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
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
 * Admin can delete, edit information for dispatchers and create new ones.
 * Mapped to: /admin-dashboard/dispatcherServlet
 * @author Artem Malchenko
 */
public class DispatchersPageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(DispatchersPageServlet.class);

    @Autowired
    private DispatcherService dispatcherService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Set<Dispatcher> dispatcherSet = dispatcherService.findAll();

            req.setAttribute("setOfDispatchers", dispatcherSet);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin_dashboard/dispatchersPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final String fullName = req.getParameter("name");
        final String passportSerialNumbers = req.getParameter("passport-serial-numbers");
        final String phoneNumber = req.getParameter("phone-number");
        final int salaryInDollars = Integer.parseInt(req.getParameter("salary-in-dollars"));

        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        final User userInfo = new User(email, hashedPassword, Role.Driver);
        final Dispatcher dispatcher = new Dispatcher(fullName, passportSerialNumbers, phoneNumber, salaryInDollars, userInfo);
        try {
            dispatcherService.save(dispatcher);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }

        resp.sendRedirect("/admin-dashboard/dispatchers");
    }
}