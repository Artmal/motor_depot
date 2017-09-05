package com.artmal.service;

import com.artmal.model.Trip;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

public interface TripService {
    Set<Trip> findAll() throws NamingException, SQLException;
}
