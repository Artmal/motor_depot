package com.artmal.dao.impl;

import com.artmal.dao.UserDao;
import com.artmal.model.users.User;
import com.artmal.service.UserService;
import com.artmal.service.impl.UserServiceImpl;
import com.artmal.utils.DatabaseUtils;
import lombok.Cleanup;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UserDaoImpl implements UserDao {
    final private DataSource dataSource = DatabaseUtils.dataSource;

    @Override
    public User findByEmail(String email) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findUserByUsername = con.prepareStatement("SELECT * FROM users WHERE  email = ?");
        findUserByUsername.setString(1, email);

        @Cleanup ResultSet usersWithTheUsername = findUserByUsername.executeQuery();
        User user = new User();
        if (!usersWithTheUsername.isBeforeFirst() ) {
            user = null;
        } else {
            usersWithTheUsername.next();
            user.setId(usersWithTheUsername.getInt("id"));
            user.setEmail(usersWithTheUsername.getString("email"));
            user.setPassword(usersWithTheUsername.getString("password"));
        }
        @Cleanup Statement st1 = con.createStatement();
        @Cleanup ResultSet roleOfTheUser = st1.executeQuery("SELECT role_id FROM user_roles WHERE user_id = " + user.getId());
        roleOfTheUser.next();
        user.setRole(DatabaseUtils.intToRole(roleOfTheUser.getInt("role_id")));

        return user;
    }

    @Override
    public int save(User user) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO users" +
                " (email, password, date_of_registration) VALUES (?, ?, NOW())");
        insertUserStatement.setString(1, user.getEmail());
        insertUserStatement.setString(2, user.getPassword());
        insertUserStatement.execute();
        @Cleanup Statement st = con.createStatement();
        @Cleanup ResultSet rs = st.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
        rs.next();
        long idOfLastInsert = rs.getInt("id");

        @Cleanup PreparedStatement setRoleForUser = con.prepareStatement("INSERT INTO user_roles (user_id, role_id) " +
                "VALUES (?, 1)");
        setRoleForUser.setLong(1, idOfLastInsert);
        UserService userService = new UserServiceImpl();

        return (int) userService.findByEmail(user.getEmail()).getId();
    }

    @Override
    public void updateUser(User user) throws NamingException, SQLException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement updateUser = con.prepareStatement("UPDATE users SET email = ?, password = ? WHERE id = ?");
        updateUser.setString(1, user.getEmail());
        updateUser.setString(2, user.getPassword());
        updateUser.setLong(3, user.getId());
        updateUser.execute();
        updateUser.close();
    }
}
