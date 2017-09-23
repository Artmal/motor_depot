package com.artmal.service;

import com.artmal.model.users.Driver;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Additional layer for {@link com.artmal.dao.DriverDao}.
 * @author Artem Malchenko
 */
public interface DriverService {
    boolean save(Driver driver) throws SQLException, NamingException;

    Driver findByUserId(long id) throws SQLException, NamingException;
    Set<Driver> findAll() throws SQLException, NamingException;
    Driver findById(long id) throws SQLException, NamingException;


}
