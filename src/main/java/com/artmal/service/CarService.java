package com.artmal.service;

import com.artmal.model.Car;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface CarService {
    boolean save(Car car) throws SQLException, NamingException;
}
