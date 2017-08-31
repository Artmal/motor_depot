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

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String username = (String) httpServletRequest.getSession().getAttribute("username");

        if(username == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        UserDao userDaoImpl = new UserDaoImpl();
        try {
            if(userDaoImpl.findByUsername(username).getRole().equals(Role.ADMIN)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
