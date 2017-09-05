package com.artmal.model;

import com.artmal.model.enums.CarType;
import com.artmal.model.enums.TripStatus;

import java.util.Date;

public class Trip {
    private int id;
    private Date dateOfCreation;
    private TripStatus tripStatus;
    private CarType carTypeRequired;
    private long carId;

    private String townFrom;
    private String townTo;
    private Date timeOut;
    private Date timeIn;

    private int paymentInDollars;
    private long dispatcherId;

    public Trip() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDateOfCreation() {
        return dateOfCreation;
    }
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    public TripStatus getTripStatus() {
        return tripStatus;
    }
    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }
    public CarType getCarTypeRequired() {
        return carTypeRequired;
    }
    public void setCarTypeRequired(CarType carTypeRequired) {
        this.carTypeRequired = carTypeRequired;
    }
    public long getCarId() {
        return carId;
    }
    public void setCarId(long carId) {
        this.carId = carId;
    }
    public String getTownFrom() {
        return townFrom;
    }
    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }
    public String getTownTo() {
        return townTo;
    }
    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }
    public Date getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }
    public Date getTimeIn() {
        return timeIn;
    }
    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }
    public int getPaymentInDollars() {
        return paymentInDollars;
    }
    public void setPaymentInDollars(int paymentInDollars) {
        this.paymentInDollars = paymentInDollars;
    }
    public long getDispatcherId() {
        return dispatcherId;
    }
    public void setDispatcherId(long dispatcherId) {
        this.dispatcherId = dispatcherId;
    }
}
