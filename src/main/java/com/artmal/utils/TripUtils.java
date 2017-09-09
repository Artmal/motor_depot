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

/**
 * Utility class which is used for operations with
 * {@link Trip} objects.
 * @author Artem Malchenko
 */
public final class TripUtils {
    private TripUtils() { }

    /**
     * In database there is the dictionary called trip_statuses(id, name), so we
     * need conversion between int and {@link TripStatus} in order to fetch entry from database.
     * @return {@link TripStatus} which corresponds statusId in trip_statuses.
     */
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

    /**
     * In database there is the dictionary called trip_statuses(id, name), so we
     * need conversion between {@link TripStatus} and int in order to save entry to database.
     * @return id in 'trip_statuses' table.
     */
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

    /**
     * From db we're getting {@link Timestamp} which need to be converted in {@link DateTime}.
     * We also getting 'yyyy-MM-dd HH:mm:ss.S' format which we need convert to 'yyyy-MM-dd HH:mm:ss'.
     */
    public static DateTime sqlTimestampToDatetime(Timestamp sqlDate) throws ParseException {
        String stringDate = sqlDate.toString().split("\\.")[0];

        DateTimeFormatter dft = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return DateTime.parse(stringDate, dft);
    }

    /**
     * Conversion between date in string format and {@link DateTime}.
     */
    public static DateTime stringDateToDateTime(String date) {
        DateTimeFormatter dft = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return DateTime.parse(date, dft);
    }


    /**
     * In order to save {@link Trip} to db we have to convert {@link DateTime} to {@link Timestamp}.
     */
    public static Timestamp dateTimeToSQLTimeStamp(DateTime dateTime) throws ParseException {
        return new Timestamp(dateTime.getMillis());
    }

    /**
     * @param trips take result set. In loop we always will get different objects from db.
     * @return constructed {@link Trip}.
     */
    public static Trip initializeTrip(ResultSet trips) throws SQLException, ParseException {
        Trip trip = new Trip();
        trip.setId(trips.getLong("id"));
        trip.setDateOfCreation(trips.getDate("date_of_creation"));
        trip.setTripStatus(TripUtils.intToStatus(trips.getInt("status_id")));
        trip.setCarTypeRequired(CarUtils.intToType(trips.getInt("car_type_id_required")));
        trip.setCarId(trips.getLong("car_id"));

        trip.setTownFrom(trips.getString("town_from"));
        trip.setTownTo(trips.getString("town_to"));
        trip.setTimeOut(TripUtils.sqlTimestampToDatetime(trips.getTimestamp("time_out")));
        trip.setTimeIn(TripUtils.sqlTimestampToDatetime(trips.getTimestamp("time_in")));
        trip.setPaymentInDollars(trips.getInt("payment_in_dollars"));
        trip.setDispatcherId(trips.getLong("dispatcher_id"));
        return trip;
    }
}