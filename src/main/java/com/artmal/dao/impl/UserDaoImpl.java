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
    public User findByEmail(String email) throws SQLException, NamingException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);

        Connection con = null;
        try {
            con = datasource.getConnection();

            PreparedStatement findUserByUsername = con.prepareStatement("SELECT * FROM users WHERE  email = ?");
            findUserByUsername.setString(1, email);
            ResultSet usersWithTheUsername = findUserByUsername.executeQuery();

            User user = new User();
            if (!usersWithTheUsername.isBeforeFirst() ) {
                user = null;
            } else {
                usersWithTheUsername.next();
                user.setId(usersWithTheUsername.getInt("id"));
                user.setEmail(usersWithTheUsername.getString("email"));
                user.setPassword(usersWithTheUsername.getString("password"));
            }

            Statement st1 = con.createStatement();
            ResultSet roleOfTheUser = st1.executeQuery("SELECT role_id FROM user_roles WHERE user_id = " + user.getId());

            roleOfTheUser.next();
            switch(roleOfTheUser.getInt("role_id")) {
                case 1: user.setRole(Role.DRIVER);
                        break;
                case 2: user.setRole(Role.DISPATCHER);
                        break;
                case 3: user.setRole(Role.ADMIN);
                        break;
            }

            usersWithTheUsername.close();
            findUserByUsername.close();
            return user;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public boolean save(User user) throws SQLException, NamingException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);
        Connection con = null;

        try {
            con = datasource.getConnection();

            PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO users" +
                    " (username, password, email) VALUES (?, ?, ?)");
            insertUserStatement.setString(1, user.getEmail());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setString(3, user.getEmail());
            insertUserStatement.execute();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
            rs.next();

            long idOfLastInsert = rs.getInt("id");
            
            PreparedStatement setRoleForUser = con.prepareStatement("INSERT INTO user_roles (user_id, role_id) " +
                    "VALUES (?, 1)");

            setRoleForUser.setLong(1, idOfLastInsert);
            
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
