package com.artmal.dao.impl;

import com.artmal.dao.TripDao;
import com.artmal.model.Trip;
import com.artmal.utils.CarUtils;
import com.artmal.utils.TripUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TripDaoImpl implements TripDao {
    @Override
    public Set<Trip> findAll() throws NamingException, SQLException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(DataSource)envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllTrips = con.prepareStatement("SELECT * FROM trips");
            ResultSet trips = findAllTrips.executeQuery();

            Set<Trip> tripSet = new HashSet();
            while(trips.next()) {
                Trip trip = new Trip();
                trip.setId(trips.getInt("id"));
                trip.setDateOfCreation(trips.getDate("date_of_creation"));
                trip.setTripStatus(TripUtils.intToStatus(trips.getInt("status_id")));
                trip.setCarTypeRequired(CarUtils.intToType(trips.getInt("car_type_id_required")));
                trip.setCarId(trips.getLong("car_id"));

                trip.setTownFrom(trips.getString("town_from"));
                trip.setTownTo(trips.getString("town_to"));
                trip.setTimeOut(trips.getDate("time_out"));
                trip.setTimeIn(trips.getDate("time_in"));
                trip.setPaymentInDollars(trips.getInt("payment_in_dollars"));
                trip.setDispatcherId(trips.getLong("dispatcher_id"));
                tripSet.add(trip);
            }

            findAllTrips.close();
            trips.close();
            return tripSet;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}
