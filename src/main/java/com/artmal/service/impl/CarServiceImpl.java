package com.artmal.service.impl;

import com.artmal.dao.CarDao;
import com.artmal.dao.impl.CarDaoImpl;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.users.Driver;
import com.artmal.service.CarService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.validation.CarAddValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    final static Logger logger = Logger.getLogger(CarServiceImpl.class);

    private static CarDao carDao = new CarDaoImpl();

    @Override
    public boolean save(Car car) throws SQLException, NamingException {
        try {
            if(!CarAddValidator.validateNumberOfSeats(String.valueOf(car.getNumberOfSeats()))) {
                throw new ValidationException(String.valueOf(car.getNumberOfSeats()));
            } else if(!CarAddValidator.validateCarColor(car.getColor())) {
                throw new ValidationException(car.getColor());
            }
        } catch (ValidationException e) {
            logger.error(e);
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
