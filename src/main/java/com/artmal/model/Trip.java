package com.artmal.model;

import com.artmal.model.enums.TripStatus;

import java.util.Date;

/**
 * Simple Java Bean for representing Trip entity.
 * @author Artem Malchenko
 */
public class Trip {
    private int id;
    private Date creationDate;
    private Date tripDate;
    private TripStatus tripStatus;
}
