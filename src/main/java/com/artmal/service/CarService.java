package com.artmal.service;

import com.artmal.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarService {
    List<Car> findAllCars() throws SQLException;
}
