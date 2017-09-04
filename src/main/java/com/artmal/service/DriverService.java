package com.artmal.service;

import com.artmal.model.users.Driver;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

public interface DriverService {
    boolean save(Driver driver) throws SQLException, NamingException;

    Set<Driver> findAll() throws SQLException;
    Driver findById(long id) throws SQLException;

}
