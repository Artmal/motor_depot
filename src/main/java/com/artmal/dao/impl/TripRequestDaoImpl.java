package com.artmal.dao.impl;

import com.artmal.dao.TripRequestDao;
import com.artmal.model.TripRequest;
import com.artmal.utils.DatabaseUtils;
import com.artmal.utils.TripRequestUtils;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TripRequestDaoImpl implements TripRequestDao {
    final private DataSource dataSource = DatabaseUtils.dataSource;


    @Autowired
    private TripRequestUtils tripRequestUtils;

    @Override
    public Set<TripRequest> findAllByTripId(long id) throws SQLException, NamingException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllTripRequestsByTripId = con.prepareStatement("SELECT * FROM trip_requests WHERE trip_id = ?");
        findAllTripRequestsByTripId.setLong(1, id);

        @Cleanup ResultSet tripRequests = findAllTripRequestsByTripId.executeQuery();
        Set<TripRequest> tripRequestSet = new HashSet<>();
        while(tripRequests.next()) {
            TripRequest tripRequest = tripRequestUtils.initializeTripRequest(tripRequests);
            tripRequestSet.add(tripRequest);
        }

        return tripRequestSet;
    }

    @Override
    public Set<TripRequest> findAllByDriverId(long id) throws NamingException, SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllTripRequestsByDriverId = con.prepareStatement("SELECT * FROM trip_requests" +
                " WHERE car_id IN(SELECT id FROM cars WHERE owner_id = ?)");
        findAllTripRequestsByDriverId.setLong(1, id);

        @Cleanup ResultSet tripRequests = findAllTripRequestsByDriverId.executeQuery();
        Set<TripRequest> tripRequestSet = new HashSet<>();

        while(tripRequests.next()) {
            TripRequest tripRequest = tripRequestUtils.initializeTripRequest(tripRequests);
            tripRequestSet.add(tripRequest);
        }

        return tripRequestSet;
    }

    @Override
    public Set<TripRequest> findAllPending() throws NamingException, SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllPendingTripRequests = con.
                prepareStatement("SELECT * FROM trip_requests WHERE date_of_confirmation IS NULL ");
        @Cleanup ResultSet tripRequests = findAllPendingTripRequests.executeQuery();
        Set<TripRequest> tripRequestSet = new HashSet<>();

        while(tripRequests.next()) {
            TripRequest tripRequest = tripRequestUtils.initializeTripRequest(tripRequests);
            tripRequestSet.add(tripRequest);
        }

        return tripRequestSet;
    }

    @Override
    public void deleteById(long id) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement deleteTripRequestById = con.prepareStatement("DELETE FROM trip_requests WHERE id = ?");
        deleteTripRequestById.setLong(1, id);
        deleteTripRequestById.execute();
    }

    @Override
    public void deleteByCarId(long id) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement deleteTripRequestById = con.prepareStatement("DELETE FROM trip_requests WHERE car_id = ?");
        deleteTripRequestById.setLong(1, id);
        deleteTripRequestById.execute();
    }

    @Override
    public long countAllPendingRequests() throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement countAllPendingTripRequests = con.
                prepareStatement("SELECT COUNT(*) FROM trip_requests WHERE date_of_confirmation IS NULL");
        @Cleanup ResultSet rs3 = countAllPendingTripRequests.executeQuery();
        rs3.next();

        return (long) rs3.getInt(1);
    }

    @Override
    public void save(TripRequest tripRequest) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement insertTripRequest = con.prepareStatement("INSERT INTO trip_requests" +
                "(trip_id, car_id, message, date_of_creation) VALUES (?, ?, ?, NOW())");
        insertTripRequest.setLong(1, tripRequest.getTripInfo().getId());
        insertTripRequest.setLong(2, tripRequest.getCarInfo().getId());
        if(tripRequest.getMessage() != null) {
            insertTripRequest.setString(3, tripRequest.getMessage());
        } else {
            insertTripRequest.setNull(3, Types.VARCHAR);
        }
        insertTripRequest.execute();
    }

    @Override
    public TripRequest findById(long id) throws NamingException, SQLException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findTripRequestById = con.prepareStatement("SELECT * FROM trip_requests WHERE id = ?");
        findTripRequestById.setLong(1, id);

        @Cleanup ResultSet tripRequest = findTripRequestById.executeQuery();
        tripRequest.next();

        return tripRequestUtils.initializeTripRequest(tripRequest);
    }
}
