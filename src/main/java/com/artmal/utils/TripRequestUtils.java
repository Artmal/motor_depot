package com.artmal.utils;

import com.artmal.model.TripRequest;
import com.artmal.service.CarService;
import com.artmal.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Utility class which is used for operations with
 * {@link TripRequest} objects.
 * @author Artem Malchenko
 */
@Component
public class TripRequestUtils {
    @Autowired
    private TripService tripService;
    @Autowired
    private CarService carService;

    public TripRequestUtils() { }

    /**
     * @param tripRequests take result set. In loop we always will get different objects from db.
     * @return constructed {@link TripRequest}.
     */
    public TripRequest initializeTripRequest(ResultSet tripRequests) throws SQLException, ParseException, NamingException {
        final TripRequest tripRequest = new TripRequest();
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
