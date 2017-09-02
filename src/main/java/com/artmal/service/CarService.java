package com.artmal.service;

import com.artmal.model.Car;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface CarService {
    List<Car> findAllCars() throws SQLException;
    boolean save(Car car) throws SQLException, NamingException;
}