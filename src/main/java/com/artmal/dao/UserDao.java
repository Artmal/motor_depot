package com.artmal.dao;

import com.artmal.model.users.User;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Data Access Object for User Entity.
 * @author Artem Malchenko
 */
public interface UserDao {
    User findByEmail(String username) throws SQLException, NamingException;
    int save(User user) throws SQLException, NamingException;
}
