package com.artmal.dao.impl;

import com.artmal.dao.CarDao;
import com.artmal.model.Car;
import com.artmal.utils.CarUtils;
import com.artmal.utils.DatabaseUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarDaoImpl implements CarDao {
    @Override
    public boolean save(Car car) throws SQLException, NamingException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);
        Connection con = null;

        try {
            con = datasource.getConnection();

            PreparedStatement insertCarStatement = con.prepareStatement("INSERT INTO cars" +
                    " (registration_number, type_id, condition_type_id, model, number_of_seats, car_color, owner_id) " +
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
}
