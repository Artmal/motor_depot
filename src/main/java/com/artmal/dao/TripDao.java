package com.artmal.dao;

import com.artmal.model.Trip;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

public interface TripDao {
    Set<Trip> findAll() throws NamingException, SQLException;
}
