package com.artmal.custom_tags;

import com.artmal.utils.Constants;
import lombok.Setter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;

public class CurrencyTag extends SimpleTagSupport {
    @Setter private Locale locale;
    @Setter private int paymentInDollars;

    public CurrencyTag() {
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter jspWriter = getJspContext().getOut();

        if(locale.getLanguage().equals("en")) {
            jspWriter.write(paymentInDollars + "$");
        } else if(locale.getLanguage().equals("ru")) {
            jspWriter.write(Constants.USD_TO_UAH * paymentInDollars + "₴");
        }
    }
}
