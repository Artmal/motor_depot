package com.artmal.service.impl;

import com.artmal.dao.TripRequestDao;
import com.artmal.model.TripRequest;
import com.artmal.service.TripRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;

@Service
public class TripRequestServiceImpl implements TripRequestService {
    @Autowired
    private TripRequestDao tripRequestDao;

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

    @Override
    public Set<TripRequest> findAllByDriverId(long id) throws NamingException, SQLException, ParseException {
        return tripRequestDao.findAllByDriverId(id);
    }

    @Override
    public Set<TripRequest> findAllPending() throws NamingException, SQLException, ParseException {
        return tripRequestDao.findAllPending();
    }

    @Override
    public void deleteById(long id) throws NamingException, SQLException {
        tripRequestDao.deleteById(id);
    }

    @Override
    public void deleteByCarId(long id) throws NamingException, SQLException {
        tripRequestDao.deleteByCarId(id);
    }
}
