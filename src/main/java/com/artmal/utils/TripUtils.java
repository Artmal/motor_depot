package com.artmal.utils;

import com.artmal.model.enums.TripStatus;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.NoSuchElementException;

public class TripUtils {
    public static TripStatus intToStatus(int statusId) {
        switch(statusId) {
            case 1:
                return TripStatus.Open;
            case 2:
                return TripStatus.In_progress;
            case 3:
                return TripStatus.Closed;
            case 4:
                return TripStatus.Canceled;
        }

        throw new NoSuchElementException();
    }

    public static int statusToInt(TripStatus tripStatus) {
        switch(tripStatus) {
            case Open:
                return 1;
            case In_progress:
                return 2;
            case Closed:
                return 3;
            case Canceled:
                return 4;
        }

        throw new NoSuchElementException();
    }

    public static DateTime sqlDateToDateTime(Timestamp sqlDate) throws ParseException {
        String stringDate = sqlDate.toString().split("\\.")[0];

        DateTimeFormatter dft = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return DateTime.parse(stringDate, dft);
    }

    public static DateTime stringDateToDateTime(String date) {
        DateTimeFormatter dft = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return DateTime.parse(date, dft);
    }

    public static Timestamp dateTimeToSQLTimeStamp(DateTime dateTime) throws ParseException {
        return new Timestamp(dateTime.getMillis());
    }
}