package com.artmal.controller;

import com.artmal.model.enums.Role;
import com.artmal.model.users.User;
import com.artmal.service.UserService;
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
import java.util.Locale;

/**
 * Servlet for logging handling.
 * Mapped to: /loginServlet
 * @author Artem Malchenko
 */
public class LoginServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Autowired
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("email");

        try {
            final User userInDb = userService.findByEmail(username);

            if (BCrypt.checkpw(req.getParameter("password"), userInDb.getPassword())) {
                req.getSession().setAttribute("id", userInDb.getId());
                req.getSession().setAttribute("email", userInDb.getEmail());
                req.getSession().setAttribute("password", userInDb.getPassword());
                req.getSession().setAttribute("role", userInDb.getRole());

                final Role role = userInDb.getRole();

                switch (role) {
                    case Driver:     resp.sendRedirect("/driver-dashboard/trips");
                        break;
                    case Dispatcher: resp.sendRedirect("/dispatcher-dashboard/trips");
                        break;
                    case Admin:      resp.sendRedirect("/admin-dashboard/trips");
                        break;
                }
            } else {
                final Locale language = (Locale) req.getSession().getAttribute("language");

                if (language.getLanguage().equals("ru")) {
                    req.setAttribute("errorText", "Неправильный электронный адрес/пароль");
                } else if (language.getLanguage().equals("en")) {
                    req.setAttribute("errorText", "Wrong email/password");
                }
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
    }
}
