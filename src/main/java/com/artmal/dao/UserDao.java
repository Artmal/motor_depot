package com.artmal.dao;

import com.artmal.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Data Access Object for User Entity.
 * @author Artem Malchenko
 */
public interface UserDao {
    User findByEmail(String username) throws SQLException, NamingException;
    boolean save(User user) throws SQLException, NamingException;
}
