package com.artmal.dao.impl;

import com.artmal.dao.DriverDao;
import com.artmal.model.users.Driver;
import com.artmal.utils.DatabaseUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DriverDao implementation.
 * @author Artem Malchenko
 */
public class DriverDaoImpl implements DriverDao {
    @Override
    public boolean save(Driver driver) throws SQLException, NamingException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);
        Connection con = null;

        try {
            con = datasource.getConnection();

            PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO drivers" +
                    " (name, passport_serial_number, phone_number, years_of_experience, date_of_birth, user_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?)");
            insertUserStatement.setString(1, driver.getName());
            insertUserStatement.setString(2, driver.getPassportSerialNumber());
            insertUserStatement.setString(3, driver.getPhoneNumber());
            insertUserStatement.setInt(4, driver.getYearsOfExperience());
            insertUserStatement.setDate(5, (Date) driver.getDateOfBirth());
            insertUserStatement.setLong(6, driver.getUserId());
            insertUserStatement.execute();

            insertUserStatement.close();
            return true;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }

    }
}
