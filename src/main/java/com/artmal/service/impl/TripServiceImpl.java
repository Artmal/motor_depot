package com.artmal.service.impl;

import com.artmal.dao.TripDao;
import com.artmal.dao.impl.TripDaoImpl;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;
import com.artmal.service.TripService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public class TripServiceImpl implements TripService {
    private static TripDao tripDao = new TripDaoImpl();

    @Override
    public boolean save(Trip trip) throws SQLException, NamingException, ParseException {
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
}
