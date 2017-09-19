package com.artmal.dao.impl;

import com.artmal.dao.CarDao;
import com.artmal.model.Car;
import com.artmal.model.Trip;
import com.artmal.model.users.Driver;
import com.artmal.service.TripRequestService;
import com.artmal.service.TripService;
import com.artmal.utils.CarUtils;
import com.artmal.utils.DatabaseUtils;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CarDaoImpl implements CarDao {
    @Autowired
    private TripRequestService tripRequestService;
    @Autowired
    private TripService tripService;

    final private DataSource dataSource = DatabaseUtils.dataSource;

    @Override
    public boolean save(final Car car) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement insertCarStatement = con.prepareStatement("INSERT INTO cars" +
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

        return true;
    }

    @Override
    public Car findById(long id) throws SQLException, NamingException, ParseException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findCarById = con.prepareStatement("SELECT * FROM cars WHERE id = ?");
        findCarById.setLong(1, id);
        
        @Cleanup ResultSet car = findCarById.executeQuery();
        car.next();
        return CarUtils.initializeCar(car);
    }

    @Override
    public Set<Car> findAllByOwnerId(long id) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllCarsByOwnerIdStatement = con.prepareStatement("SELECT * FROM cars WHERE owner_id = ?");
        findAllCarsByOwnerIdStatement.setLong(1, id);
        @Cleanup ResultSet cars = findAllCarsByOwnerIdStatement.executeQuery();

        Set<Car> carSet = new HashSet<>();
        while(cars.next()) {
            Car car = CarUtils.initializeCar(cars);
            carSet.add(car);
        }
        return carSet;
    }

    @Override
    public Set<Car> findAll() throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllCars = con.prepareStatement("SELECT * FROM cars");
        @Cleanup ResultSet cars = findAllCars.executeQuery();

        Set<Car> carSet = new HashSet<>();
        while(cars.next()) {
            Car car = CarUtils.initializeCar(cars);
            carSet.add(car);
        }

        return carSet;
    }

    @Override
    public Set<Car> findSuitableForTripDriverCars(Driver driver, Trip trip) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllSuitableCars = con.prepareStatement("SELECT * FROM cars WHERE owner_id = ? AND type_id = ?");
        findAllSuitableCars.setLong(1, driver.getId());
        findAllSuitableCars.setInt(2, CarUtils.typeToInt(trip.getCarTypeRequired()));

        @Cleanup ResultSet cars = findAllSuitableCars.executeQuery();

        Set<Car> carSet = new HashSet<>();
        while(cars.next()) {
            Car car = CarUtils.initializeCar(cars);
            carSet.add(car);
        }

        return carSet;
    }

    @Override
    public void updateCar(Car car) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement updateCar = con.prepareStatement("UPDATE cars SET registration_number = ?, model = ?,"
                + " type_id = ?, condition_type_id = ?, number_of_seats = ?, color = ? WHERE id = ?");

        updateCar.setString(1, car.getRegistrationNumber());
        updateCar.setString(2, car.getModel());
        updateCar.setInt(3, CarUtils.typeToInt(car.getType()));
        updateCar.setInt(4, CarUtils.conditionTypeToInt(car.getCondition()));
        updateCar.setInt(5, car.getNumberOfSeats());
        updateCar.setString(6, car.getColor());
        updateCar.setLong(7, car.getId());
        updateCar.execute();
    }

    @Override
    public void deleteById(long id) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();
        con.setAutoCommit(false);

        tripRequestService.deleteByCarId(id);
        tripService.deleteByCarId(id);

        @Cleanup PreparedStatement deleteCarById = con.prepareStatement("DELETE FROM cars WHERE id = ?");
        deleteCarById.setLong(1, id);
        deleteCarById.execute();
        con.commit();
    }
}
