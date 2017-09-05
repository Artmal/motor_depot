package com.artmal.dao.impl;

import com.artmal.dao.CarDao;
import com.artmal.model.Car;
import com.artmal.utils.CarUtils;

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

public class CarDaoImpl implements CarDao {
    @Override
    public boolean save(Car car) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(DataSource)envContext.lookup("jdbc/TestDB");
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
    public Set<Car> findAllByOwnerId(long id) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllCarsByOwnerIdStatement = con.prepareStatement("SELECT * FROM cars WHERE owner_id = ?");
            findAllCarsByOwnerIdStatement.setLong(1, id);
            ResultSet cars = findAllCarsByOwnerIdStatement.executeQuery();

            Set<Car> carSet = new HashSet();
            while(cars.next()) {
                Car car = new Car();
                car.setRegistrationNumber(cars.getString("registration_number"));
                car.setType(CarUtils.intToType(cars.getInt("type_id")));
                car.setCondition(CarUtils.intToCondition(cars.getInt("condition_type_id")));
                car.setModel(cars.getString("model"));
                car.setNumberOfSeats(cars.getInt("number_of_seats"));
                car.setColor(cars.getString("color"));
                car.setOwnerId(cars.getLong("owner_id"));
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
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(DataSource)envContext.lookup("jdbc/TestDB");

        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllCars = con.prepareStatement("SELECT * FROM cars");
            ResultSet cars = findAllCars.executeQuery();

            Set<Car> carSet = new HashSet();
            while(cars.next()) {
                Car car = new Car();
                car.setRegistrationNumber(cars.getString("registration_number"));
                car.setType(CarUtils.intToType(cars.getInt("type_id")));
                car.setCondition(CarUtils.intToCondition(cars.getInt("condition_type_id")));
                car.setModel(cars.getString("model"));
                car.setNumberOfSeats(cars.getInt("number_of_seats"));
                car.setColor(cars.getString("color"));
                car.setOwnerId(cars.getLong("owner_id"));
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
}
