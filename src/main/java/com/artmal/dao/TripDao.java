package com.artmal.dao;

import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

/**
 * Data Access Object for {@link Trip}.
 * @author Artem Malchenko
 */
public interface TripDao {
    boolean save(Trip trip) throws SQLException, NamingException, ParseException;

    Trip findById(long id) throws SQLException, NamingException, ParseException;
    Set<Trip> findAll() throws NamingException, SQLException, ParseException;
    Set<Trip> findAllByDriverId(long id) throws NamingException, SQLException, ParseException;
    Set<Trip> findAllByDispatcherId(long id) throws NamingException, SQLException, ParseException;
    Set<Trip> findAllOpen() throws SQLException, ParseException;


    void assignCarToTheTrip(Trip trip, Car car) throws SQLException, NamingException;
    void setTripStatus(Trip trip, TripStatus tripStatus) throws NamingException, SQLException;
    void nullifyResponsibleCarColumn(Trip trip) throws NamingException, SQLException;

    void deleteByCarId(long id) throws NamingException, SQLException;
}