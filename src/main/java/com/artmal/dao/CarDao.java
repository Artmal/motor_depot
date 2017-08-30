package com.artmal.dao;

import com.artmal.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {
    List<Car> findAllCars() throws SQLException;
}
