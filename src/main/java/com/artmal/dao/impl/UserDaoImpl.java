package com.artmal.dao.impl;

import com.artmal.dao.UserDao;
import com.artmal.model.User;
import com.artmal.model.enums.Role;
import com.artmal.utils.DatabaseUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.NamingException;
import java.sql.*;

/**
 * UserDAO implementation.
 * @author Artem Malchenko
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User findByUsername(String username) throws SQLException, NamingException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);

        Connection con = null;

        try {
            con = datasource.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username = \"admin\"");

            User user = new User();
            if (!rs.isBeforeFirst() ) {
                user = null;
            } else {
                rs.next();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select role_id FROM user_roles WHERE user_id = " + user.getId());

            rs1.next();
            switch(rs1.getInt("role_id")) {
                case 1: user.setRole(Role.DRIVER);
                        break;
                case 2: user.setRole(Role.DISPATCHER);
                        break;
                case 3: user.setRole(Role.ADMIN);
                        break;
            }

            rs.close();
            st.close();
            return user;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public boolean save(User user) throws SQLException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);
        Connection con = null;

        try {
            con = datasource.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username = \"admin\"");

            return true;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }

    }
}
