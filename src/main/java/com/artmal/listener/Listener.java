package com.artmal.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Basic ServletContextListener for logging common operations.
 * @author Artem Malchenko
 */
public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    final static Logger logger = Logger.getLogger(Listener.class);


    public Listener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Servlet context initialized.");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      logger.info("Servlet context destroyed.");
    }

    public void sessionCreated(HttpSessionEvent se) {
      logger.info("Session created.");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      logger.info("Session destroyed.");
    }

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      logger.info("Attribute \"" + sbe.getName()+ "\" with value " + sbe.getValue() + " added");
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      logger.info("Attribute \"" + sbe.getName() + "\" with value " + sbe.getValue() + " was deleted");
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      logger.info("Attribute \"" + sbe.getName() + "\" with value " + sbe.getValue() + " was replaced");
    }
}
