package com.artmal.dao.impl;

import com.artmal.dao.UserDao;
import com.artmal.model.users.User;
import com.artmal.service.UserService;
import com.artmal.service.impl.UserServiceImpl;
import com.artmal.utils.DatabaseUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * UserDAO implementation.
 * @author Artem Malchenko
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User findByEmail(String email) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");

        Connection con = null;
        try {
            con = dataSource.getConnection();

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
            user.setRole(DatabaseUtils.intToRole(roleOfTheUser.getInt("role_id")));

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
    public int save(User user) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO users" +
                    " (email, password, date_of_registration) VALUES (?, ?, NOW())");
            insertUserStatement.setString(1, user.getEmail());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.execute();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
            rs.next();

            long idOfLastInsert = rs.getInt("id");
            
            PreparedStatement setRoleForUser = con.prepareStatement("INSERT INTO user_roles (user_id, role_id) " +
                    "VALUES (?, 1)");

            setRoleForUser.setLong(1, idOfLastInsert);
            st.close();
            rs.close();
            insertUserStatement.close();

            UserService userService = new UserServiceImpl();
            return (int) userService.findByEmail(user.getEmail()).getId();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}
