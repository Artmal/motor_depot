package com.artmal.model;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * Simple Java Bean for representing TripRequest entity.
 * {@link com.artmal.model.users.Driver} makes request for {@link Trip}.
 * @author Artem Malchenko
 */
public @Data class TripRequest {
    private long id;
    private Trip tripInfo;
    private Car carInfo;
    private String message;
    private DateTime dateOfCreation;
    private DateTime dateOfConfirmation;

    public TripRequest() {
    }

    public TripRequest(Trip tripInfo, Car carInfo) {
        this.tripInfo = tripInfo;
        this.carInfo = carInfo;
    }

    public TripRequest(Trip tripInfo, Car carInfo, String message) {
        this.tripInfo = tripInfo;
        this.carInfo = carInfo;
        this.message = message;
    }
}
