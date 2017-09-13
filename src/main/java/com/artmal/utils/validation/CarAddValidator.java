package com.artmal.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for server-side input validation during car adding process.
 * @author Artem Malchenko
 */
public final class CarAddValidator {
    private CarAddValidator() { }

    public static boolean validateNumberOfSeats(String numberOfSeats) {
        Pattern checkNumberOfSeats = Pattern.compile("^[1-9][\\d]?$");
        Matcher matcher = checkNumberOfSeats.matcher(numberOfSeats);

        return matcher.find();
    }

    public static boolean validateCarColor(String color) {
        Pattern checkCarColor = Pattern.compile("^[A-ZА-Я][a-zа-я]+$");
        Matcher matcher = checkCarColor.matcher(color);

        return matcher.find();
    }

}
