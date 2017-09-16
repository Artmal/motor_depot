package com.artmal.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LanguageChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locale locale = (Locale) req.getSession().getAttribute("language");
            if(locale == null) {
                setEnLocale(req);
            } else {
                if(req.getParameter("languageChange") != null) {
                    if(locale.getLanguage().equals("en")) {
                        Locale ruLocale = new Locale.Builder().setLanguage("ru").build();
                        req.getSession().setAttribute("language", ruLocale);
                    } else if(locale.getLanguage().equals("ru")) {
                        setEnLocale(req);
                    }
                }
            }
    }

    private void setEnLocale(HttpServletRequest req) {
        Locale enLocale = new Locale.Builder().setLanguage("en").build();
        req.getSession().setAttribute("language", enLocale);
    }
}
