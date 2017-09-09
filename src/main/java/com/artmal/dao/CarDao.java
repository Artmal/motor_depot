package com.artmal.dao;

import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.users.Driver;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public interface CarDao {
    boolean save(Car car) throws SQLException, NamingException;

    Car findById(long id) throws SQLException, NamingException, ParseException;
    Set<Car> findAllByOwnerId(long id) throws SQLException, NamingException;
    Set<Car> findAll() throws SQLException, NamingException;
    Set<Car> findSuitableForTripDriverCars(Driver driver, Trip trip) throws NamingException, SQLException;
}