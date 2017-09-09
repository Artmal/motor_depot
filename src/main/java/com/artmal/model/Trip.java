package com.artmal.model;

import com.artmal.model.enums.CarType;
import com.artmal.model.enums.TripStatus;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Dispatchers create trips and drivers make requests to them.
 * @author Artem Malchenko
 */
public class Trip {
    private long id;
    private Date dateOfCreation;
    private TripStatus tripStatus;
    private CarType carTypeRequired;
    private long carId;

    private String townFrom;
    private String townTo;
    private DateTime timeOut;
    private DateTime timeIn;

    private int paymentInDollars;
    private long dispatcherId;

    public Trip() {
    }

    public Trip(TripStatus tripStatus, CarType carTypeRequired, String townFrom, String townTo, DateTime timeOut, DateTime timeIn, int paymentInDollars, long dispatcherId) {
        this.tripStatus = tripStatus;
        this.carTypeRequired = carTypeRequired;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
        this.paymentInDollars = paymentInDollars;
        this.dispatcherId = dispatcherId;
    }

    public Trip(TripStatus tripStatus, CarType carTypeRequired, String townFrom, String townTo, DateTime timeOut, DateTime timeIn, int paymentInDollars) {
        this.tripStatus = tripStatus;
        this.carTypeRequired = carTypeRequired;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
        this.paymentInDollars = paymentInDollars;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public DateTime getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(DateTime timeOut) {
        this.timeOut = timeOut;
    }
    public DateTime getTimeIn() {
        return timeIn;
    }
    public void setTimeIn(DateTime timeIn) {
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
