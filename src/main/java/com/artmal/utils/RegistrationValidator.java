package com.artmal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for input validation.
 * @author Artem Malchenko
 */
public final class RegistrationValidator {
    private RegistrationValidator() { }

    public static boolean validateEmail(String email) {
        Pattern checkEmail = Pattern.compile("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$");
        Matcher matcher = checkEmail.matcher(email);

        return matcher.find();
    }

    public static boolean validateFullName(String fullName) {
        Pattern checkFullName = Pattern.compile("^[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+$");
        Matcher matcher = checkFullName.matcher(fullName);

        return matcher.find();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern checkFullName = Pattern.compile("^(1[ \\-\\+]{0,3}|\\+1[ -\\+]{0,3}|\\+1|\\+)?((\\(\\+?1-[2-9][0-9]" +
                "{1,2}\\))|(\\(\\+?[2-8][0-9][0-9]\\))|(\\(\\+?[1-9][0-9]\\))|(\\(\\+?[17]\\))|(\\([2-9][2-9]\\))|" +
                "([ \\-\\.]{0,3}[0-9]{2,4}))?([ \\-\\.][0-9])?([ \\-\\.]{0,3}[0-9]{2,4}){2,3}$");
        Matcher matcher = checkFullName.matcher(phoneNumber);

        return matcher.find();
    }

    public static boolean validateAge(String age) {
        Pattern checkAge = Pattern.compile("^[1-9][\\d]{0,2}$");
        Matcher matcher = checkAge.matcher(age);

        return matcher.find();
    }
}
