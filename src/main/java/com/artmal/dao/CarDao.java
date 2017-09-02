package com.artmal.dao;

import com.artmal.model.Car;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object for Car Entity.
 * @author Artem Malchenko
 */
public interface CarDao {
    List<Car> findAllCars() throws SQLException;
    boolean save(Car car) throws SQLException, NamingException;
}
