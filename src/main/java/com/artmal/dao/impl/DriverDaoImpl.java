package com.artmal.dao.impl;

import com.artmal.dao.DriverDao;
import com.artmal.model.users.Driver;
import com.artmal.utils.DatabaseUtils;
import lombok.Cleanup;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class DriverDaoImpl implements DriverDao {
    final private DataSource dataSource = DatabaseUtils.dataSource;

    @Override
    public Driver findById(final long id) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findDriverByIdStatement = con.prepareStatement("SELECT * FROM drivers WHERE id = ?");
        findDriverByIdStatement.setLong(1, id);

        @Cleanup ResultSet driver = findDriverByIdStatement.executeQuery();
        driver.next();
        Driver theDriver = new Driver();
        theDriver.setId(driver.getInt("id"));
        theDriver.setName(driver.getString("name"));
        theDriver.setPassportSerialNumbers(driver.getString("passport_serial_numbers"));
        theDriver.setPhoneNumber(driver.getString("phone_number"));

        return theDriver;
    }

    @Override
    public boolean save(Driver driver) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        // Insert to users
        @Cleanup PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO users" +
                " (email, password, date_of_registration, reg_admin_id) VALUES (?, ?, NOW(), ?)");
        insertUserStatement.setString(1, driver.getUserInfo().getEmail());
        insertUserStatement.setString(2, driver.getUserInfo().getPassword());
        insertUserStatement.setLong(3, driver.getUserInfo().getRegAdminId());
        insertUserStatement.execute();

        // Insert to user_roles
        @Cleanup Statement st = con.createStatement();
        @Cleanup ResultSet rs = st.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
        rs.next();
        long idOfLastInsert = rs.getInt("id");
        @Cleanup PreparedStatement setRoleForUser = con.prepareStatement("INSERT INTO user_roles (user_id, role_id) "
                + "VALUES (?, 1)");
        setRoleForUser.setLong(1, idOfLastInsert);
        setRoleForUser.execute();

        @Cleanup PreparedStatement insertDriverStatement = con.prepareStatement("INSERT INTO drivers (name, "
                + "passport_serial_numbers, phone_number, age, user_id) VALUES (?, ?, ?, ?, ?)");
        insertDriverStatement.setString(1, driver.getName());
        insertDriverStatement.setString(2, driver.getPassportSerialNumbers());
        insertDriverStatement.setString(3, driver.getPhoneNumber());
        insertDriverStatement.setInt(4, driver.getAge());
        insertDriverStatement.setLong(5, idOfLastInsert);
        insertDriverStatement.execute();

        return true;
    }

    @Override
    public Driver findByUserId(long id) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findDriverByUserIdStatement = con.prepareStatement("SELECT * FROM drivers WHERE user_id = ?");
        findDriverByUserIdStatement.setLong(1, id);

        @Cleanup ResultSet driver = findDriverByUserIdStatement.executeQuery();
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
    }

    @Override
    public Set<Driver> findAll() throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllDriversStatement = con.prepareStatement("SELECT * FROM drivers ORDER BY id");
        @Cleanup ResultSet drivers = findAllDriversStatement.executeQuery();

        Set<Driver> driverSet = new HashSet<>();
        while(drivers.next()) {
            Driver driver = new Driver();
            driver.setId(drivers.getInt("id"));
            driver.setName(drivers.getString("name"));
            driver.setPassportSerialNumbers(drivers.getString("passport_serial_numbers"));
            driver.setPhoneNumber(drivers.getString("phone_number"));
            driverSet.add(driver);
        }

        return driverSet;
    }
}
