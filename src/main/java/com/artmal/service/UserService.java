package com.artmal.service;

import com.artmal.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface UserService {
    User findByEmail(String username) throws SQLException, NamingException;
    boolean save(User user) throws SQLException, NamingException;
}
