package com.artmal.service.impl;

import com.artmal.dao.CarDao;
import com.artmal.dao.impl.CarDaoImpl;
import com.artmal.model.Car;
import com.artmal.service.CarService;

import javax.naming.NamingException;
import java.sql.SQLException;

public class CarServiceImpl implements CarService {
    private static CarDao carDao = new CarDaoImpl();

    @Override
    public boolean save(Car car) throws SQLException, NamingException {
        return carDao.save(car);
    }
}
