package com.artmal.service;

import com.artmal.model.TripRequest;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public interface TripRequestService {
    void save(TripRequest tripRequest) throws SQLException, NamingException;

    TripRequest findById(long id) throws NamingException, SQLException, ParseException;
    Set<TripRequest> findAllByTripId(long id) throws SQLException, NamingException, ParseException;
    Set<TripRequest> findAllByDriverId(long id) throws NamingException, SQLException, ParseException;

    void deleteById(long id) throws NamingException, SQLException;
}
