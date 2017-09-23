package com.artmal.service.impl;

import com.artmal.dao.TripDao;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;
import com.artmal.service.TripService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.validation.TripValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

@Service
@Log4j
public class TripServiceImpl implements TripService {
    @Autowired
    private TripDao tripDao;

    @Override
    public boolean save(Trip trip) throws SQLException, NamingException, ParseException {
        try {
            if(!TripValidator.validateTown(trip.getTownFrom())) {
                throw new ValidationException(trip.getTownFrom());
            } else if(!TripValidator.validateTown(trip.getTownTo())) {
                throw new ValidationException(trip.getTownTo());
            } else if(!TripValidator.validatePayment(String.valueOf(trip.getPaymentInDollars()))) {
                throw new ValidationException(String.valueOf(trip.getPaymentInDollars()));
            } else if(!TripValidator.validateTime(String.valueOf(trip.getTimeOut()))) {
                throw new ValidationException((String.valueOf(trip.getTimeOut())));
            } else if(!TripValidator.validateTime(String.valueOf(trip.getTimeIn()))) {
                throw new ValidationException(String.valueOf(trip.getTimeIn()));
            }
        } catch (ValidationException e) {
            log.error(e);
        }

        return tripDao.save(trip);
    }

    @Override
    public Trip findById(long id) throws SQLException, NamingException, ParseException {
        return tripDao.findById(id);
    }

    @Override
    public Set<Trip> findAll() throws NamingException, SQLException, ParseException {
        return tripDao.findAll();
    }

    @Override
    public Set<Trip> findAllByDriverId(long id) throws NamingException, SQLException, ParseException {
        return tripDao.findAllByDriverId(id);
    }

    @Override
    public void assignCarToTheTrip(Trip trip, Car car) throws SQLException, NamingException {
        tripDao.assignCarToTheTrip(trip, car);
    }

    @Override
    public void setTripStatus(Trip trip, TripStatus tripStatus) throws NamingException, SQLException {
        tripDao.setTripStatus(trip, tripStatus);
    }

    @Override
    public void nullifyResponsibleCarColumn(Trip trip) throws NamingException, SQLException {
        tripDao.nullifyResponsibleCarColumn(trip);
    }

    @Override
    public void updateTrip(Trip trip) throws SQLException, ParseException {
        tripDao.updateTrip(trip);
    }

    @Override
    public Set<Trip> findAllByDispatcherId(long id) throws NamingException, SQLException, ParseException {
        return tripDao.findAllByDispatcherId(id);
    }

    @Override
    public Set<Trip> findAllOpen() throws SQLException, ParseException {
        return tripDao.findAllOpen();
    }

    @Override
    public Set<Trip> findAllByCarId(long carId) throws SQLException, ParseException {
        return tripDao.findAllByCarId(carId);
    }

    @Override
    public void deleteByCarId(long id) throws NamingException, SQLException {
        tripDao.deleteByCarId(id);
    }

    @Override
    public void deleteById(long id) throws NamingException, SQLException {
        tripDao.deleteById(id);
    }
}
