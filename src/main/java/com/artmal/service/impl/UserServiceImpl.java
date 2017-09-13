package com.artmal.service.impl;

import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.users.User;
import com.artmal.service.UserService;
import com.artmal.utils.validation.RegistrationValidator;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static UserDao userDao = new UserDaoImpl();

    @Override
    public User findByEmail(String email) throws SQLException, NamingException {
        return userDao.findByEmail(email);
    }

    @Override
    public int save(User user) throws SQLException, NamingException {
        if(RegistrationValidator.validateEmail(user.getEmail())){
            // ???
        }

        // ???

        return userDao.save(user);
    }

    @Override
    public void updateUser(User user) throws NamingException, SQLException {
        userDao.updateUser(user);
    }
}