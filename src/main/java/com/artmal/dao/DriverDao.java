package com.artmal.dao;

import com.artmal.model.users.Driver;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Data Access Object for Driver Entity.
 * @author Artem Malchenko
 */
public interface DriverDao {
    Driver findById(long id) throws SQLException;
    boolean save(Driver driver) throws SQLException, NamingException;
    Set<Driver> findAll() throws SQLException;
}
