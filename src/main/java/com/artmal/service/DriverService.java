package com.artmal.service;

import com.artmal.model.users.Driver;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface DriverService {
    boolean save(Driver driver) throws SQLException, NamingException;
}
