package com.artmal.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for server-side input validation during trip creation process.
 * @author Artem Malchenko
 */
public final class TripValidator {
    private TripValidator() { }

    public static boolean validateTown(String town) {
        Pattern checkTown = Pattern.compile("^[A-ZА-Я][a-zа-я-\\s]+$");
        Matcher matcher = checkTown.matcher(town);

        return matcher.find();
    }

    public static boolean validateTime(String time) {
        Pattern checkTime = Pattern.compile("^[2][\\d]{3}-[\\d][1-9]-[\\d][1-9] [\\d]{2}:[\\d]{2}:[\\d]{2}$");
        Matcher matcher = checkTime.matcher(time);

        return matcher.find();
    }

    public static boolean validatePayment(String payment) {
        Pattern checkPayment = Pattern.compile("^[1-9][\\d]*$");
        Matcher matcher = checkPayment.matcher(payment);

        return matcher.find();
    }
}
