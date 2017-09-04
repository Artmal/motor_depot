package com.artmal.dao;

import com.artmal.model.Car;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface CarDao {
    boolean save(Car car) throws SQLException, NamingException;
}
