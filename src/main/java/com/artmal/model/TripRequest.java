package com.artmal.model;

import org.joda.time.DateTime;

/**
 * Simple Java Bean for representing TripRequest entity.
 * {@link com.artmal.model.users.Driver} makes request for {@link Trip}.
 * @author Artem Malchenko
 */
public class TripRequest {
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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Trip getTripInfo() {
        return tripInfo;
    }
    public void setTripInfo(Trip tripInfo) {
        this.tripInfo = tripInfo;
    }
    public Car getCarInfo() {
        return carInfo;
    }
    public void setCarInfo(Car carInfo) {
        this.carInfo = carInfo;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public DateTime getDateOfCreation() {
        return dateOfCreation;
    }
    public void setDateOfCreation(DateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    public DateTime getDateOfConfirmation() {
        return dateOfConfirmation;
    }
    public void setDateOfConfirmation(DateTime dateOfConfirmation) {
        this.dateOfConfirmation = dateOfConfirmation;
    }
}
