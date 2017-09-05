package com.artmal.dao.impl;

import com.artmal.dao.DriverDao;
import com.artmal.model.users.Driver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver findById(long id) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findDriverByIdStatement = con.prepareStatement("SELECT * FROM drivers WHERE id = ?");
            findDriverByIdStatement.setLong(1, id);
            ResultSet driver = findDriverByIdStatement.executeQuery();
            driver.next();

            Driver theDriver = new Driver();
            theDriver.setId(driver.getInt("id"));
            theDriver.setName(driver.getString("name"));
            theDriver.setPassportSerialNumbers(driver.getString("passport_serial_numbers"));
            theDriver.setPhoneNumber(driver.getString("phone_number"));

            findDriverByIdStatement.close();
            driver.close();
            return theDriver;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public boolean save(Driver driver) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            // Insert to users
            PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO users" +
                    " (email, password, date_of_registration, reg_admin_id) VALUES (?, ?, NOW(), ?)");

            insertUserStatement.setString(1, driver.getUserInfo().getEmail());
            insertUserStatement.setString(2, driver.getUserInfo().getPassword());
            insertUserStatement.setLong(3, driver.getUserInfo().getRegAdminId());
            insertUserStatement.execute();

            // Insert to user_roles
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
            rs.next();

            long idOfLastInsert = rs.getInt("id");
            PreparedStatement setRoleForUser = con.prepareStatement("INSERT INTO user_roles (user_id, role_id) " +
                    "VALUES (?, 1)");

            setRoleForUser.setLong(1, idOfLastInsert);
            setRoleForUser.execute();

            PreparedStatement insertDriverStatement = con.prepareStatement("INSERT INTO drivers (name, " +
                    "passport_serial_numbers, phone_number, age, user_id) VALUES (?, ?, ?, ?, ?)");

            insertDriverStatement.setString(1, driver.getName());
            insertDriverStatement.setString(2, driver.getPassportSerialNumbers());
            insertDriverStatement.setString(3, driver.getPhoneNumber());
            insertDriverStatement.setInt(4, driver.getAge());
            insertDriverStatement.setLong(5, idOfLastInsert);
            insertDriverStatement.execute();

            insertUserStatement.close();
            st.close();
            rs.close();
            return true;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Driver findByUserId(long id) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findDriverByUserIdStatement = con.prepareStatement("SELECT * FROM drivers WHERE user_id = ?");
            findDriverByUserIdStatement.setLong(1, id);
            ResultSet driver = findDriverByUserIdStatement.executeQuery();
            driver.next();

            Driver theDriver = new Driver();
            theDriver.setId(driver.getInt("id"));
            theDriver.setName(driver.getString("name"));
            theDriver.setPassportSerialNumbers(driver.getString("passport_serial_numbers"));
            theDriver.setPhoneNumber(driver.getString("phone_number"));
            theDriver.setAge(driver.getInt("age"));

            findDriverByUserIdStatement.close();
            driver.close();
            return theDriver;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Set<Driver> findAll() throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllDriversStatement = con.prepareStatement("SELECT * FROM drivers ORDER BY id");
            ResultSet drivers = findAllDriversStatement.executeQuery();

            Set<Driver> driverSet = new HashSet();
            while(drivers.next()) {
                Driver driver = new Driver();
                driver.setId(drivers.getInt("id"));
                driver.setName(drivers.getString("name"));
                driver.setPassportSerialNumbers(drivers.getString("passport_serial_numbers"));
                driver.setPhoneNumber(drivers.getString("phone_number"));
                driverSet.add(driver);
            }

            findAllDriversStatement.close();
            drivers.close();

            return driverSet;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}
