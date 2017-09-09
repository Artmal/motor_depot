package com.artmal.dao;

import com.artmal.model.TripRequest;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

/**
 * Data Access Object for {@link TripRequestDao}.
 * @author Artem Malchenko
 */
public interface TripRequestDao {
    void save(TripRequest tripRequest) throws SQLException, NamingException;

    TripRequest findById(long id) throws NamingException, SQLException, ParseException;
    Set<TripRequest> findAllByTripId(long id) throws SQLException, NamingException, ParseException;
    Set<TripRequest> findAllByDriverId(long id) throws NamingException, SQLException, ParseException;

    void deleteById(long id) throws NamingException, SQLException;
}
