package com.artmal.dao;

import com.artmal.model.users.Driver;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface DriverDao {
    boolean save(Driver driver) throws SQLException, NamingException;
}
