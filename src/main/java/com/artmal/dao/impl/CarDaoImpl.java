package com.artmal.dao.impl;

import com.artmal.dao.CarDao;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.users.Driver;
import com.artmal.service.TripRequestService;
import com.artmal.service.TripService;
import com.artmal.service.impl.TripRequestServiceImpl;
import com.artmal.service.impl.TripServiceImpl;
import com.artmal.utils.CarUtils;
import com.artmal.utils.DatabaseUtils;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class CarDaoImpl implements CarDao {
    private TripRequestService tripRequestService = new TripRequestServiceImpl();
    private TripService tripService = new TripServiceImpl();

    private DataSource dataSource = DatabaseUtils.initializeDataSource();

    @Override
    public boolean save(final Car car) throws SQLException, NamingException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement insertCarStatement = con.prepareStatement("INSERT INTO cars" +
                    " (registration_number, type_id, condition_type_id, model, number_of_seats, color, owner_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");

            insertCarStatement.setString(1, car.getRegistrationNumber());
            insertCarStatement.setInt(2, CarUtils.typeToInt(car.getType()));
            insertCarStatement.setInt(3, CarUtils.conditionTypeToInt(car.getCondition()));
            insertCarStatement.setString(4, car.getModel());
            insertCarStatement.setInt(5, car.getNumberOfSeats());
            insertCarStatement.setString(6, car.getColor());
            insertCarStatement.setLong(7, car.getOwnerId());
            insertCarStatement.execute();

            insertCarStatement.close();
            return true;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Car findById(long id) throws SQLException, NamingException, ParseException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findCarById = con.prepareStatement("SELECT * FROM cars WHERE id = ?");
            findCarById.setLong(1, id);
            ResultSet car = findCarById.executeQuery();
            car.next();

            Car theCar = CarUtils.initializeCar(car);

            findCarById.close();
            car.close();
            return theCar;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Set<Car> findAllByOwnerId(long id) throws SQLException, NamingException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllCarsByOwnerIdStatement = con.prepareStatement("SELECT * FROM cars WHERE owner_id = ?");
            findAllCarsByOwnerIdStatement.setLong(1, id);
            ResultSet cars = findAllCarsByOwnerIdStatement.executeQuery();

            Set<Car> carSet = new HashSet();
            while(cars.next()) {
                Car car = CarUtils.initializeCar(cars);
                carSet.add(car);

            }
            findAllCarsByOwnerIdStatement.close();
            cars.close();
            return carSet;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Set<Car> findAll() throws SQLException, NamingException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllCars = con.prepareStatement("SELECT * FROM cars");
            ResultSet cars = findAllCars.executeQuery();

            Set<Car> carSet = new HashSet();
            while(cars.next()) {
                Car car = CarUtils.initializeCar(cars);
                carSet.add(car);
            }

            findAllCars.close();
            cars.close();
            return carSet;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Set<Car> findSuitableForTripDriverCars(Driver driver, Trip trip) throws NamingException, SQLException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllSuitableCars = con.prepareStatement("SELECT * FROM cars WHERE owner_id = ? AND type_id = ?");
            findAllSuitableCars.setLong(1, driver.getId());
            findAllSuitableCars.setInt(2, CarUtils.typeToInt(trip.getCarTypeRequired()));

            ResultSet cars = findAllSuitableCars.executeQuery();

            Set<Car> carSet = new HashSet();
            while(cars.next()) {
                Car car = CarUtils.initializeCar(cars);
                carSet.add(car);
            }

            findAllSuitableCars.close();
            cars.close();
            return carSet;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public void updateCar(Car car) throws NamingException, SQLException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement updateCar = con.prepareStatement("UPDATE cars SET registration_number = ?, model = ?," +
                            " type_id = ?, condition_type_id = ?, number_of_seats = ?, color = ? WHERE id = ?");

            updateCar.setString(1, car.getRegistrationNumber());
            updateCar.setString(2, car.getModel());
            updateCar.setInt(3, CarUtils.typeToInt(car.getType()));
            updateCar.setInt(4, CarUtils.conditionTypeToInt(car.getCondition()));
            updateCar.setInt(5, car.getNumberOfSeats());
            updateCar.setString(6, car.getColor());
            updateCar.setLong(7, car.getId());
            updateCar.execute();

            updateCar.close();
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

            tripRequestService.deleteByCarId(id);
            tripService.deleteByCarId(id);

            PreparedStatement deleteCarById = con.prepareStatement("DELETE FROM cars WHERE id = ?");
            deleteCarById.setLong(1, id);
            deleteCarById.execute();

            deleteCarById.close();
        } finally {
            if (con != null) {
                try {
                    con.close();
            } catch (Exception ignore) {
            }
        }
        }
    }
}
