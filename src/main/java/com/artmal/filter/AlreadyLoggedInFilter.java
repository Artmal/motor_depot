package com.artmal.filter;


import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.enums.Role;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 */
public class AlreadyLoggedInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String username = (String) httpServletRequest.getSession().getAttribute("username");
        if(username != null) {
            UserDao userDao = new UserDaoImpl();

            Role userRole = null;
            try {
                userRole = userDao.findByEmail(username).getRole();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }

            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            switch(userRole) {
                case Driver:     httpResponse.sendRedirect("/Driver-panel");
                case Dispatcher: httpResponse.sendRedirect("/Dispatcher-panel");
                case Admin:      httpResponse.sendRedirect("/Admin-panel");
            }
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
