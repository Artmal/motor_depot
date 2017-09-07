package com.artmal.service.impl;

import com.artmal.dao.CarDao;
import com.artmal.dao.impl.CarDaoImpl;
import com.artmal.model.Car;
import com.artmal.service.CarService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public class CarServiceImpl implements CarService {
    private static CarDao carDao = new CarDaoImpl();

    @Override
    public boolean save(Car car) throws SQLException, NamingException {
        return carDao.save(car);
    }

    @Override
    public Car findById(long id) throws SQLException, NamingException, ParseException {
        return carDao.findById(id);
    }

    @Override
    public Set<Car> findAllByOwnerId(long id) throws SQLException, NamingException {
        return carDao.findAllByOwnerId(id);
    }

    @Override
    public Set<Car> findAll() throws SQLException, NamingException {
        return carDao.findAll();
    }
}
