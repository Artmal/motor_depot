package com.artmal.dao.impl;

import com.artmal.dao.DriverDao;
import com.artmal.model.users.Driver;
import com.artmal.utils.DatabaseUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.NamingException;
import java.sql.*;

public class DriverDaoImpl implements DriverDao {
    @Override
    public boolean save(Driver driver) throws SQLException, NamingException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);
        Connection con = null;

        try {
            con = datasource.getConnection();

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
}
