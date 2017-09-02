package com.artmal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistrationUtil {
    public static Date stringToDate(String stringDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
        Date date = format.parse(stringDate);
        return date;
    }
}
