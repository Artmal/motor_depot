package com.artmal.filter;

import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.enums.Role;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminFilter implements Filter {
    final static Logger logger = Logger.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String email = (String) httpServletRequest.getSession().getAttribute("email");

        if(email == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        UserDao userDaoImpl = new UserDaoImpl();
        try {
            if(userDaoImpl.findByEmail(email).getRole().equals(Role.ADMIN)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (SQLException e) {
            logger.error("Threw a SQLException in AdminFilter::doFilter, full stack trace follows:", e);
        } catch (NamingException e) {
            logger.error("Threw a NamingException in AdminFilter::doFilter, full stack trace follows:", e);
        }
    }

    @Override
    public void destroy() {

    }
}
