package com.artmal.service;

import com.artmal.model.Car;
import com.artmal.model.Trip;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public interface TripService {
    boolean save(Trip trip) throws SQLException, NamingException, ParseException;

    Trip findById(long id) throws SQLException, NamingException, ParseException;
    Set<Trip> findAll() throws NamingException, SQLException, ParseException;

    void assignCarToTheTrip(Trip trip, Car car) throws SQLException, NamingException;
}
