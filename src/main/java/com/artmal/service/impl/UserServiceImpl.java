package com.artmal.service.impl;

import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.User;
import com.artmal.service.UserService;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static UserDao userDaoImpl = new UserDaoImpl();

    @Override
    public User findByUsername(String username) throws SQLException, NamingException {
        return userDaoImpl.findByUsername(username);
    }

    @Override
    public boolean save(User user) throws SQLException, NamingException {
        return userDaoImpl.save(user);
    }
}