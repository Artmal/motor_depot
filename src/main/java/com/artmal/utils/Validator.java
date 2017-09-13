package com.artmal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for input validation.
 * @author Artem Malchenko
 */
public class Validator {
    public static boolean validateEmail(String email) {
        Pattern checkEmail = Pattern.compile("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$");
        Matcher matcher = checkEmail.matcher(email);

        return matcher.find();
    }

    public static boolean validateTown(String town) {
        Pattern checkTown = Pattern.compile("^[A-ZА-Я][a-zа-я-\\s]+$");
        Matcher matcher = checkTown.matcher(town);

        return matcher.find();
    }

    public static boolean validateFullName(String fullName) {
        Pattern checkFullName = Pattern.compile("^[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+$");
        Matcher matcher = checkFullName.matcher(fullName);

        return matcher.find();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern checkFullName = Pattern.compile("^[+?][d]+");
        Matcher matcher = checkFullName.matcher(phoneNumber);

        return matcher.find();
    }

    public static boolean validateAge(String age) {
        Pattern checkAge = Pattern.compile("^[1-9]{1,3}$");
        Matcher matcher = checkAge.matcher(age);

        return matcher.find();
    }
}
