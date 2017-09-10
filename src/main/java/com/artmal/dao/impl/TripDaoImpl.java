package com.artmal.dao.impl;

import com.artmal.dao.TripDao;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;
import com.artmal.utils.CarUtils;
import com.artmal.utils.TripUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class TripDaoImpl implements TripDao {
    @Override
    public boolean save(Trip trip) throws SQLException, NamingException, ParseException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement insertTripStatement = con.prepareStatement("INSERT INTO trips" +
                    " (date_of_creation, status_id, car_type_id_required, town_from, town_to, time_out, time_in," +
                    " payment_in_dollars, dispatcher_id) VALUES (NOW(), ?, ?, ?, ?, ?, ?, ?, ?)");

            insertTripStatement.setInt(1, TripUtils.statusToInt(trip.getTripStatus()));
            insertTripStatement.setInt(2, CarUtils.typeToInt(trip.getCarTypeRequired()));
            insertTripStatement.setString(3, trip.getTownFrom());
            insertTripStatement.setString(4, trip.getTownTo());
            insertTripStatement.setTimestamp(5, TripUtils.dateTimeToSQLTimeStamp(trip.getTimeOut()));
            insertTripStatement.setTimestamp(6, TripUtils.dateTimeToSQLTimeStamp(trip.getTimeIn()));
            insertTripStatement.setInt(7, trip.getPaymentInDollars());
            if (trip.getDispatcherId() != 0) {
                insertTripStatement.setLong(8, trip.getDispatcherId());
            } else {
                insertTripStatement.setNull(8, java.sql.Types.INTEGER);
            }
            insertTripStatement.execute();

            insertTripStatement.close();
            return true;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Trip findById(long id) throws SQLException, NamingException, ParseException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (javax.sql.DataSource) envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findTripById = con.prepareStatement("SELECT * FROM trips WHERE id = ?");
            findTripById.setLong(1, id);
            ResultSet trip = findTripById.executeQuery();
            trip.next();

            Trip theTrip = new Trip();
            theTrip.setId(trip.getLong("id"));
            theTrip.setDateOfCreation(trip.getDate("date_of_creation"));
            theTrip.setTripStatus(TripUtils.intToStatus(trip.getInt("status_id")));
            theTrip.setCarTypeRequired(CarUtils.intToType(trip.getInt("car_type_id_required")));
            theTrip.setCarId(trip.getLong("car_id"));

            theTrip.setTownFrom(trip.getString("town_from"));
            theTrip.setTownTo(trip.getString("town_to"));
            theTrip.setTimeOut(TripUtils.sqlTimestampToDatetime(trip.getTimestamp("time_out")));
            theTrip.setTimeIn(TripUtils.sqlTimestampToDatetime(trip.getTimestamp("time_in")));
            theTrip.setPaymentInDollars(trip.getInt("payment_in_dollars"));
            theTrip.setDispatcherId(trip.getLong("dispatcher_id"));

            findTripById.close();
            trip.close();
            return theTrip;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Set<Trip> findAll() throws NamingException, SQLException, ParseException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllTrips = con.prepareStatement("SELECT * FROM trips");
            ResultSet trips = findAllTrips.executeQuery();

            Set<Trip> tripSet = new HashSet();
            while (trips.next()) {
                Trip trip = new Trip();
                trip.setId(trips.getLong("id"));
                trip.setDateOfCreation(trips.getDate("date_of_creation"));
                trip.setTripStatus(TripUtils.intToStatus(trips.getInt("status_id")));
                trip.setCarTypeRequired(CarUtils.intToType(trips.getInt("car_type_id_required")));
                trip.setCarId(trips.getLong("car_id"));

                trip.setTownFrom(trips.getString("town_from"));
                trip.setTownTo(trips.getString("town_to"));
                trip.setTimeOut(TripUtils.sqlTimestampToDatetime(trips.getTimestamp("time_out")));
                trip.setTimeIn(TripUtils.sqlTimestampToDatetime(trips.getTimestamp("time_in")));
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

    @Override
    public Set<Trip> findAllByDriverId(long id) throws NamingException, SQLException, ParseException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllTrips = con.prepareStatement("SELECT * FROM trips WHERE car_id IN(SELECT car_id FROM trip_requests WHERE date_of_confirmation IS NOT NULL AND car_id IN\n" +
                    "(SELECT id FROM cars WHERE owner_id = ?))");
            findAllTrips.setLong(1, id);
            ResultSet trips = findAllTrips.executeQuery();

            Set<Trip> tripSet = new HashSet();
            while (trips.next()) {
                Trip trip = TripUtils.initializeTrip(trips);
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

    @Override
    public void assignCarToTheTrip(Trip trip, Car car) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement assignCar = con.prepareStatement("UPDATE trips SET status_id = 2, car_id = ? WHERE id = ?");
            assignCar.setLong(1, car.getId());
            assignCar.setLong(2, trip.getId());
            assignCar.execute();

            PreparedStatement setConfirmationDateForRequest = con.prepareStatement("UPDATE trip_requests SET date_of_confirmation = NOW() WHERE car_id = ?");
            setConfirmationDateForRequest.setLong(1, car.getId());
            setConfirmationDateForRequest.execute();

            assignCar.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void setTripStatus(Trip trip, TripStatus tripStatus) throws NamingException, SQLException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement setTripStatus = con.prepareStatement("UPDATE trips SET status_id = ? WHERE id = ?");
            setTripStatus.setInt(1, TripUtils.statusToInt(tripStatus));
            setTripStatus.setLong(2, trip.getId());
            setTripStatus.execute();
            setTripStatus.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void nullifyResponsibleCarColumn(Trip trip) throws NamingException, SQLException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement setTripStatus = con.prepareStatement("UPDATE trips SET car_id = ? WHERE id = ?");
            setTripStatus.setNull(1, Types.INTEGER);
            setTripStatus.setLong(2, trip.getId());
            setTripStatus.execute();
            setTripStatus.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void deleteByCarId(long id) throws NamingException, SQLException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement deleteByCarId = con.prepareStatement("DELETE  FROM trips WHERE car_id = ?");
            deleteByCarId.setLong(1, id);
            deleteByCarId.execute();

            deleteByCarId.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}