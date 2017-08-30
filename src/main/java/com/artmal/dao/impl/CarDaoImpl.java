package com.artmal.dao.impl;

import com.artmal.dao.CarDao;
import com.artmal.model.Car;
import com.artmal.utils.DatabaseUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

            return cars;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}
