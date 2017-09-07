package com.artmal.service;

import com.artmal.model.Car;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public interface CarService {
    boolean save(Car car) throws SQLException, NamingException;

    Car findById(long id) throws SQLException, NamingException, ParseException;
    Set<Car> findAllByOwnerId(long id) throws SQLException, NamingException;
    Set<Car> findAll() throws SQLException, NamingException;
}
