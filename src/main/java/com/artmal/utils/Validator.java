package com.artmal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for input validation.
 * @author Artem Malchenko
 */
public class Validator {
    public static boolean validateEmail(String email) {
        Pattern checkEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
        Matcher matcher = checkEmail.matcher(email);

        return matcher.find();
    }

    public static boolean validateTown(String town) {
        Pattern checkTown = Pattern.compile("^[A-ZА-Я][a-zа-я-\\s]+$");
        Matcher matcher = checkTown.matcher(town);

        return matcher.find();
    }

    public static boolean validateFullName(String fullName) {
        Pattern checkFullName = Pattern.compile("^[a-z ,.'-]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = checkFullName.matcher(fullName);

        return matcher.find();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern checkFullName = Pattern.compile("^[+?][d]+");
        Matcher matcher = checkFullName.matcher(phoneNumber);

        return matcher.find();
    }
}
