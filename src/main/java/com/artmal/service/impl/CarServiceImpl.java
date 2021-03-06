package com.artmal.service.impl;

import com.artmal.dao.CarDao;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.validation.CarAddValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

@Service
@Log4j
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public boolean save(Car car) throws SQLException, NamingException, ValidationException {
        if(!CarAddValidator.validateNumberOfSeats(String.valueOf(car.getNumberOfSeats()))) {
            throw new ValidationException(String.valueOf(car.getNumberOfSeats()));
        } else if(!CarAddValidator.validateCarColor(car.getColor())) {
            throw new ValidationException(car.getColor());
        }

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

    @Override
    public Set<Car> findSuitableForTripDriverCars(Driver driver, Trip trip) throws NamingException, SQLException {
        return carDao.findSuitableForTripDriverCars(driver, trip);
    }

    @Override
    public void updateCar(Car car) throws NamingException, SQLException {
        carDao.updateCar(car);
    }

    @Override
    public void deleteById(long id) throws NamingException, SQLException {
        carDao.deleteById(id);
    }
}
