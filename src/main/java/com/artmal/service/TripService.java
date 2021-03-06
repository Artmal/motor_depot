package com.artmal.service;

import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;
import com.artmal.utils.ValidationException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

/**
 * Additional layer for {@link com.artmal.dao.TripDao}.
 * @author Artem Malchenko
 */
public interface TripService {
    boolean save(Trip trip) throws SQLException, NamingException, ParseException, ValidationException;

    Trip findById(long id) throws SQLException, NamingException, ParseException;
    Set<Trip> findAll() throws NamingException, SQLException, ParseException;
    Set<Trip> findAllByDriverId(long id) throws NamingException, SQLException, ParseException;
    Set<Trip> findAllByDispatcherId(long id) throws NamingException, SQLException, ParseException;
    Set<Trip> findAllOpen() throws SQLException, ParseException;
    Set<Trip> findAllByCarId(long carId) throws SQLException, ParseException;

    void assignCarToTheTrip(Trip trip, Car car) throws SQLException, NamingException;
    void setTripStatus(Trip trip, TripStatus tripStatus) throws NamingException, SQLException;
    void nullifyResponsibleCarColumn(Trip trip) throws NamingException, SQLException;

    void updateTrip(Trip trip) throws SQLException, ParseException;

    void deleteByCarId(long id) throws NamingException, SQLException;
    void deleteById(long id) throws NamingException, SQLException;

}
