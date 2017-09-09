package com.artmal.utils;

import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static Trip initializeTrip(ResultSet trips) throws SQLException, ParseException {
        Trip trip = new Trip();
        trip.setId(trips.getLong("id"));
        trip.setDateOfCreation(trips.getDate("date_of_creation"));
        trip.setTripStatus(TripUtils.intToStatus(trips.getInt("status_id")));
        trip.setCarTypeRequired(CarUtils.intToType(trips.getInt("car_type_id_required")));
        trip.setCarId(trips.getLong("car_id"));

        trip.setTownFrom(trips.getString("town_from"));
        trip.setTownTo(trips.getString("town_to"));
        trip.setTimeOut(TripUtils.sqlDateToDateTime(trips.getTimestamp("time_out")));
        trip.setTimeIn(TripUtils.sqlDateToDateTime(trips.getTimestamp("time_in")));
        trip.setPaymentInDollars(trips.getInt("payment_in_dollars"));
        trip.setDispatcherId(trips.getLong("dispatcher_id"));
        return trip;
    }
}