package com.artmal.service;

import com.artmal.model.users.User;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Additional layer for {@link com.artmal.dao.UserDao}.
 * @author Artem Malchenko
 */
public interface UserService {
    User findByEmail(String username) throws SQLException, NamingException;
    int save(User user) throws SQLException, NamingException;

    void updateUser(User user) throws NamingException, SQLException;
}
