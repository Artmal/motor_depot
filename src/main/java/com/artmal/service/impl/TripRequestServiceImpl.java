package com.artmal.service.impl;

import com.artmal.dao.TripRequestDao;
import com.artmal.dao.impl.TripRequestDaoImpl;
import com.artmal.model.TripRequest;
import com.artmal.service.TripRequestService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

public class TripRequestServiceImpl implements TripRequestService {
    private static TripRequestDao tripRequestDao = new TripRequestDaoImpl();

    @Override
    public void save(TripRequest tripRequest) throws SQLException, NamingException {
        tripRequestDao.save(tripRequest);
    }

    @Override
    public TripRequest findById(long id) throws NamingException, SQLException, ParseException {
        return tripRequestDao.findById(id);
    }

    @Override
    public Set<TripRequest> findAllByTripId(long id) throws SQLException, NamingException, ParseException {
       return tripRequestDao.findAllByTripId(id);
    }
}