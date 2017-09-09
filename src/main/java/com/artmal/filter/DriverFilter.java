package com.artmal.filter;

import com.artmal.model.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Restricts unauthorized access for drivers's page.
 * Filters /driver-dashboard/*
 * @author Artem Malchenko
 */
public class DriverFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Role role = (Role) httpServletRequest.getSession().getAttribute("role");

        if(role.name().equals("Driver")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
