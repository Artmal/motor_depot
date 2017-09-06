package com.artmal.dao;

import com.artmal.model.Trip;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public interface TripDao {
    boolean save(Trip trip) throws SQLException, NamingException, ParseException;

    Trip findById(long id) throws SQLException, NamingException;
    Set<Trip> findAll() throws NamingException, SQLException, ParseException;
}
