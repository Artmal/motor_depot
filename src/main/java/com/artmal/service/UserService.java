package com.artmal.service;

import com.artmal.model.users.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface UserService {
    User findByEmail(String username) throws SQLException, NamingException;
    int save(User user) throws SQLException, NamingException;
}
