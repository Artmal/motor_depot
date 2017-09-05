package com.artmal.service.impl;

import com.artmal.dao.TripDao;
import com.artmal.dao.impl.TripDaoImpl;
import com.artmal.model.Trip;
import com.artmal.service.TripService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

public class TripServiceImpl implements TripService {
    private static TripDao tripDao = new TripDaoImpl();

    @Override
    public Set<Trip> findAll() throws NamingException, SQLException {
        return tripDao.findAll();
    }
}
