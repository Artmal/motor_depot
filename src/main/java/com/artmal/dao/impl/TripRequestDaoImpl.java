package com.artmal.dao.impl;

import com.artmal.dao.TripRequestDao;
import com.artmal.model.TripRequest;
import com.artmal.utils.DatabaseUtils;
import com.artmal.utils.TripRequestUtils;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class TripRequestDaoImpl implements TripRequestDao {
    private DataSource dataSource = DatabaseUtils.initializeDataSource();

    @Override
    public Set<TripRequest> findAllByTripId(long id) throws SQLException, NamingException, ParseException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllTripRequestsByTripId = con.prepareStatement("SELECT * FROM trip_requests WHERE trip_id = ?");
            findAllTripRequestsByTripId.setLong(1, id);
            ResultSet tripRequests = findAllTripRequestsByTripId.executeQuery();

            Set<TripRequest> tripRequestSet = new HashSet();
            while(tripRequests.next()) {
                TripRequest tripRequest = TripRequestUtils.initializeTripRequest(tripRequests);
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
    public Set<TripRequest> findAllByDriverId(long id) throws NamingException, SQLException, ParseException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllTripRequestsByDriverId = con.prepareStatement("SELECT * FROM trip_requests" +
                    " WHERE car_id IN(SELECT id FROM cars WHERE owner_id = ?)");
            findAllTripRequestsByDriverId.setLong(1, id);
            ResultSet tripRequests = findAllTripRequestsByDriverId.executeQuery();

            Set<TripRequest> tripRequestSet = new HashSet();
            while(tripRequests.next()) {
                TripRequest tripRequest = TripRequestUtils.initializeTripRequest(tripRequests);
                tripRequestSet.add(tripRequest);
            }

            findAllTripRequestsByDriverId.close();
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
    public Set<TripRequest> findAllPending() throws NamingException, SQLException, ParseException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllPendingTripRequests = con.
                    prepareStatement("SELECT * FROM trip_requests WHERE date_of_confirmation IS NULL ");
            ResultSet tripRequests = findAllPendingTripRequests.executeQuery();

            Set<TripRequest> tripRequestSet = new HashSet();
            while(tripRequests.next()) {
                TripRequest tripRequest = TripRequestUtils.initializeTripRequest(tripRequests);
                tripRequestSet.add(tripRequest);
            }

            findAllPendingTripRequests.close();
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
    public void deleteById(long id) throws NamingException, SQLException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement deleteTripRequestById = con.prepareStatement("DELETE FROM trip_requests WHERE id = ?");
            deleteTripRequestById.setLong(1, id);
            deleteTripRequestById.execute();

            deleteTripRequestById.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void deleteByCarId(long id) throws NamingException, SQLException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement deleteTripRequestById = con.prepareStatement("DELETE FROM trip_requests WHERE car_id = ?");
            deleteTripRequestById.setLong(1, id);
            deleteTripRequestById.execute();

            deleteTripRequestById.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public long countAllPendingRequests() throws NamingException, SQLException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement countAllPendingTripRequests = con.
                    prepareStatement("SELECT COUNT(*) FROM trip_requests WHERE date_of_confirmation IS NULL");

            ResultSet rs3 = countAllPendingTripRequests.executeQuery();
            rs3.next();
            long numberOfPendingRequests = rs3.getInt(1);
            countAllPendingTripRequests.close();

            return numberOfPendingRequests;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void save(TripRequest tripRequest) throws SQLException, NamingException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement insertTripRequest = con.prepareStatement("INSERT INTO trip_requests" +
                    "(trip_id, car_id, message, date_of_creation) VALUES (?, ?, ?, NOW())");

            insertTripRequest.setLong(1, tripRequest.getTripInfo().getId());
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
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findTripRequestById = con.prepareStatement("SELECT * FROM trip_requests WHERE id = ?");
            findTripRequestById.setLong(1, id);
            ResultSet tripRequest = findTripRequestById.executeQuery();
            tripRequest.next();

            TripRequest theTripRequest = TripRequestUtils.initializeTripRequest(tripRequest);

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
