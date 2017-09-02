package com.artmal.service.impl;

import com.artmal.dao.CarDao;
import com.artmal.dao.impl.CarDaoImpl;
import com.artmal.model.Car;
import com.artmal.service.CarService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl implements CarService {
    private static CarDao carDaoImpl = new CarDaoImpl();

    @Override
    public List<Car> findAllCars() throws SQLException {
        return carDaoImpl.findAllCars();
    }

    @Override
    public boolean save(Car car) throws SQLException, NamingException {
        return carDaoImpl.save(car);
    }
}
