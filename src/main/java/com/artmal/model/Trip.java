package com.artmal.model;

import com.artmal.model.enums.CarType;
import com.artmal.model.enums.TripStatus;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Dispatchers create trips and drivers make requests to them.
 * @author Artem Malchenko
 */
public @Data class Trip {
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
}
