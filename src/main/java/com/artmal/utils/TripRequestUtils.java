package com.artmal.utils;

import com.artmal.model.TripRequest;
import com.artmal.service.CarService;
import com.artmal.service.TripService;
import com.artmal.service.impl.CarServiceImpl;
import com.artmal.service.impl.TripServiceImpl;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Utility class which is used for operations with
 * {@link TripRequest} objects.
 * @author Artem Malchenko
 */
public final class TripRequestUtils {
    private static TripService tripService = new TripServiceImpl();
    private static CarService carService = new CarServiceImpl();

    private TripRequestUtils() { }

    /**
     * @param tripRequests take result set. In loop we always will get different objects from db.
     * @return constructed {@link TripRequest}.
     */
    public static TripRequest initializeTripRequest(ResultSet tripRequests) throws SQLException, ParseException, NamingException {
        TripRequest tripRequest = new TripRequest();
        tripRequest.setId(tripRequests.getLong("id"));
        tripRequest.setTripInfo(tripService.findById(tripRequests.getLong("trip_id")));
        tripRequest.setCarInfo(carService.findById(tripRequests.getLong("car_id")));
        tripRequest.setDateOfCreation(TripUtils.sqlTimestampToDatetime(tripRequests.getTimestamp("date_of_creation")));

        if(tripRequests.getTimestamp("date_of_confirmation") != null) {
            tripRequest.setDateOfConfirmation(TripUtils.sqlTimestampToDatetime(tripRequests.getTimestamp("date_of_confirmation")));
        }

        return tripRequest;
    }
}
