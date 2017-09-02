package com.artmal.dao.impl;

import com.artmal.dao.CarDao;
import com.artmal.model.Car;
import com.artmal.utils.DatabaseUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CarDao implementation.
 * @author Artem Malchenko
 */
public class CarDaoImpl implements CarDao {
    @Override
    public List<Car> findAllCars() throws SQLException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);

        Connection con = null;

        try {
            con = datasource.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cars");

            List<Car> cars = new ArrayList<>();
            while(rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setRegistrationNumber(rs.getString("registration_number"));
                car.setCarType(rs.getString("car_type"));
                car.setManufacturer(rs.getString("manufacturer"));
                car.setModel(rs.getString("model"));
                car.setProductionYear(rs.getInt("production_year"));
                car.setNumberOfSeats(rs.getInt("number_of_seats"));
                car.setColor(rs.getString("car_color"));
                car.setMileage(rs.getInt("mileage"));
                car.setCarCondition(rs.getString("car_condition"));

                cars.add(car);
            }

            st.close();
            rs.close();
            return cars;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public boolean save(Car car) throws SQLException, NamingException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);

        Connection con = null;
        try {
            con = datasource.getConnection();

            PreparedStatement insertCar = con.prepareStatement("INSERT INTO cars (registration_number, car_type," +
                    " manufacturer, model, production_year, number_of_seats, car_color, mileage, car_condition)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            insertCar.setString(1, car.getRegistrationNumber());
            insertCar.setString(2, car.getCarType());
            insertCar.setString(3, car.getManufacturer());
            insertCar.setString(4, car.getModel());
            insertCar.setInt(5, car.getProductionYear());
            insertCar.setInt(6, car.getNumberOfSeats());
            insertCar.setString(7, car.getColor());
            insertCar.setInt(8, car.getMileage());
            insertCar.setString(9, car.getCarCondition());
            insertCar.execute();

            insertCar.close();
            return true;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }

    }
}
