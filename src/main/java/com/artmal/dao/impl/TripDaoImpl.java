package com.artmal.dao.impl;

import com.artmal.dao.TripDao;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.enums.TripStatus;
import com.artmal.utils.CarUtils;
import com.artmal.utils.DatabaseUtils;
import com.artmal.utils.TripUtils;
import lombok.Cleanup;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TripDaoImpl implements TripDao {
    final private DataSource dataSource = DatabaseUtils.dataSource;

    @Override
    public boolean save(Trip trip) throws SQLException, NamingException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement insertTripStatement = con.prepareStatement("INSERT INTO trips" +
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

        return true;
    }

    @Override
    public Trip findById(long id) throws SQLException, NamingException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findTripById = con.prepareStatement("SELECT * FROM trips WHERE id = ?");
        findTripById.setLong(1, id);
        @Cleanup ResultSet trip = findTripById.executeQuery();
        trip.next();

        return TripUtils.initializeTrip(trip);
    }

    @Override
    public Set<Trip> findAll() throws NamingException, SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllTrips = con.prepareStatement("SELECT * FROM trips");
        @Cleanup ResultSet trips = findAllTrips.executeQuery();
        Set<Trip> tripSet = new HashSet<>();
        while (trips.next()) {
            Trip trip = TripUtils.initializeTrip(trips);
            tripSet.add(trip);
        }

        return tripSet;
    }

    @Override
    public Set<Trip> findAllByDriverId(long id) throws NamingException, SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllTrips = con.prepareStatement("SELECT * FROM trips WHERE car_id IN(SELECT car_id FROM trip_requests WHERE date_of_confirmation IS NOT NULL AND car_id IN\n" +
                "(SELECT id FROM cars WHERE owner_id = ?))");
        findAllTrips.setLong(1, id);
        @Cleanup ResultSet trips = findAllTrips.executeQuery();
        Set<Trip> tripSet = new HashSet<>();
        while (trips.next()) {
            Trip trip = TripUtils.initializeTrip(trips);
            tripSet.add(trip);
        }

        return tripSet;
    }

    @Override
    public Set<Trip> findAllByDispatcherId(long id) throws NamingException, SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllByDispatcherId = con.prepareStatement("SELECT  * FROM trips WHERE dispatcher_id = ?");
        findAllByDispatcherId.setLong(1, id);
        @Cleanup ResultSet trips = findAllByDispatcherId.executeQuery();
        Set<Trip> tripSet = new HashSet<>();
        while (trips.next()) {
            Trip trip = TripUtils.initializeTrip(trips);
            tripSet.add(trip);
        }

        return tripSet;
    }

    @Override
    public Set<Trip> findAllOpen() throws SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllOpenTrips = con.prepareStatement("SELECT  * FROM trips WHERE status_id = ?");
        int openStatusId = 1;
        findAllOpenTrips.setLong(1, openStatusId);

        @Cleanup ResultSet trips = findAllOpenTrips.executeQuery();
        Set<Trip> tripSet = new HashSet<>();
        while (trips.next()) {
            Trip trip = TripUtils.initializeTrip(trips);
            tripSet.add(trip);
        }

        return tripSet;
    }

    @Override
    public Set<Trip> findAllByCarId(long carId) throws SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();
        @Cleanup PreparedStatement findAllByCarId = con.prepareStatement("SELECT * FROM trips WHERE car_id = ?");
        findAllByCarId.setLong(1, carId);

        @Cleanup ResultSet trips = findAllByCarId.executeQuery();
        Set<Trip> tripSet = new HashSet<>();
        while (trips.next()) {
            Trip trip = TripUtils.initializeTrip(trips);
            tripSet.add(trip);
        }

        return tripSet;
    }

    @Override
    public void assignCarToTheTrip(Trip trip, Car car) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement assignCar = con.prepareStatement("UPDATE trips SET status_id = 2, car_id = ? WHERE id = ?");
        assignCar.setLong(1, car.getId());
        assignCar.setLong(2, trip.getId());
        assignCar.execute();

        @Cleanup PreparedStatement setConfirmationDateForRequest = con.prepareStatement("UPDATE trip_requests SET date_of_confirmation = NOW() WHERE car_id = ?");
        setConfirmationDateForRequest.setLong(1, car.getId());

        setConfirmationDateForRequest.execute();
    }

    @Override
    public void setTripStatus(Trip trip, TripStatus tripStatus) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement setTripStatus = con.prepareStatement("UPDATE trips SET status_id = ? WHERE id = ?");
        setTripStatus.setInt(1, TripUtils.statusToInt(tripStatus));
        setTripStatus.setLong(2, trip.getId());

        setTripStatus.execute();
    }

    @Override
    public void nullifyResponsibleCarColumn(Trip trip) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement setTripStatus = con.prepareStatement("UPDATE trips SET car_id = ? WHERE id = ?");
        setTripStatus.setNull(1, Types.INTEGER);
        setTripStatus.setLong(2, trip.getId());

        setTripStatus.execute();
    }

    @Override
    public void updateTrip(Trip trip) throws SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement updateTrip = con.prepareStatement("UPDATE trips SET status_id = ?,"
                + " car_type_id_required = ?, town_from = ?, town_to = ?, time_out = ?, time_in = ?,"
                + " payment_in_dollars = ? WHERE id = ?");

        updateTrip.setInt(1, TripUtils.statusToInt(trip.getTripStatus()));
        updateTrip.setInt(2, CarUtils.typeToInt(trip.getCarTypeRequired()));
        updateTrip.setString(3, trip.getTownFrom());
        updateTrip.setString(4, trip.getTownTo());
        updateTrip.setTimestamp(5, TripUtils.dateTimeToSQLTimeStamp(trip.getTimeOut()));
        updateTrip.setTimestamp(6, TripUtils.dateTimeToSQLTimeStamp(trip.getTimeIn()));
        updateTrip.setInt(7, trip.getPaymentInDollars());
        updateTrip.setLong(8, trip.getId());
        updateTrip.execute();
    }

    @Override
    public void deleteByCarId(long id) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement deleteByCarId = con.prepareStatement("DELETE FROM trips WHERE car_id = ?");
        deleteByCarId.setLong(1, id);

        deleteByCarId.execute();
    }

    @Override
    public void deleteById(long id) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();
        con.setAutoCommit(false);

        @Cleanup PreparedStatement deleteAssociatedTripRequests = con.
                prepareStatement("DELETE FROM trip_requests WHERE trip_id = ?");
        deleteAssociatedTripRequests.setLong(1, id);
        deleteAssociatedTripRequests.execute();

        @Cleanup PreparedStatement deleteTripById = con.prepareStatement("DELETE FROM trips WHERE id = ?");
        deleteTripById.setLong(1, id);
        deleteTripById.execute();

        con.commit();
    }
}