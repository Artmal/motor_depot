package com.artmal.service.impl;

import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.users.User;
import com.artmal.service.UserService;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static UserDao userDao = new UserDaoImpl();

    @Override
    public User findByEmail(String username) throws SQLException, NamingException {
        return userDao.findByEmail(username);
    }

    @Override
    public int save(User user) throws SQLException, NamingException {
        return userDao.save(user);
    }
}