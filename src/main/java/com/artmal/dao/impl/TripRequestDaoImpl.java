package com.artmal.dao.impl;

import com.artmal.dao.TripRequestDao;
import com.artmal.model.TripRequest;
import com.artmal.service.CarService;
import com.artmal.service.TripService;
import com.artmal.service.impl.CarServiceImpl;
import com.artmal.service.impl.TripServiceImpl;
import com.artmal.utils.TripUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class TripRequestDaoImpl implements TripRequestDao {
    private TripService tripService = new TripServiceImpl();
    private CarService carService = new CarServiceImpl();

    @Override
    public Set<TripRequest> findAllByTripId(long id) throws SQLException, NamingException, ParseException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllTripRequestsByTripId = con.prepareStatement("SELECT * FROM trip_requests WHERE trip_id = ?");
            findAllTripRequestsByTripId.setLong(1, id);
            ResultSet tripRequests = findAllTripRequestsByTripId.executeQuery();

            Set<TripRequest> tripRequestSet = new HashSet();
            while(tripRequests.next()) {
                TripRequest tripRequest = new TripRequest();
                tripRequest.setId(tripRequests.getLong("id"));
                tripRequest.setTripInfo(tripService.findById(tripRequests.getLong("trip_id")));
                tripRequest.setCarInfo(carService.findById(tripRequests.getLong("car_id")));
                tripRequest.setDateOfCreation(TripUtils.sqlDateToDateTime(tripRequests.getTimestamp("date_of_creation")));

                if(tripRequests.getTimestamp("date_of_confirmation") != null) {
                    tripRequest.setDateOfConfirmation(TripUtils.sqlDateToDateTime(tripRequests.getTimestamp("date_of_confirmation")));
                }

                tripRequestSet.add(tripRequest);

            }
            findAllTripRequestsByTripId.close();
            tripRequests.close();
            return tripRequestSet;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void save(TripRequest tripRequest) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement insertTripRequest = con.prepareStatement("INSERT INTO trip_requests" +
                    "(trip_id, car_id, message, date_of_creation) VALUES (?, ?, ?, NOW())");

            insertTripRequest.setLong(1, tripRequest.getId());
            insertTripRequest.setLong(2, tripRequest.getCarInfo().getId());
            if(tripRequest.getMessage() != null) {
                insertTripRequest.setString(3, tripRequest.getMessage());
            } else {
                insertTripRequest.setNull(3, Types.VARCHAR);
            }
            insertTripRequest.execute();
            insertTripRequest.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public TripRequest findById(long id) throws NamingException, SQLException, ParseException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findTripRequestById = con.prepareStatement("SELECT * FROM trip_requests WHERE id = ?");
            findTripRequestById.setLong(1, id);
            ResultSet tripRequest = findTripRequestById.executeQuery();
            tripRequest.next();

            TripRequest theTripRequest = new TripRequest();
            theTripRequest.setId(tripRequest.getLong("id"));
            theTripRequest.setTripInfo(tripService.findById(tripRequest.getLong("trip_id")));
            theTripRequest.setCarInfo(carService.findById(tripRequest.getLong("car_id")));
            theTripRequest.setDateOfCreation(TripUtils.sqlDateToDateTime(tripRequest.getTimestamp("date_of_creation")));

            findTripRequestById.close();
            tripRequest.close();
            return theTripRequest;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}