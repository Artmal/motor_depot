package com.artmal.custom_tags;

import com.artmal.service.TripRequestService;
import com.artmal.service.impl.TripRequestServiceImpl;

import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Used in view part(admin's and dispatcher's sidebar).
 * @author Artem Malchenko
 */
public class CountPendingRequestsTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        TripRequestService tripRequestService = new TripRequestServiceImpl();

        JspWriter jspWriter = getJspContext().getOut();
        try {
            jspWriter.write(String.valueOf(tripRequestService.countAllPendingRequests()));
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
}
