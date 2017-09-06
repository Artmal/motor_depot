package com.artmal.service;

import com.artmal.model.Trip;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public interface TripService {
    boolean save(Trip trip) throws SQLException, NamingException, ParseException;

    Set<Trip> findAll() throws NamingException, SQLException, ParseException;
}
