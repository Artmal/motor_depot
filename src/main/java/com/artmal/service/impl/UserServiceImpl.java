package com.artmal.service.impl;

import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.users.User;
import com.artmal.service.UserService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private static UserDao userDao = new UserDaoImpl();

    @Override
    public User findByEmail(String email) throws SQLException, NamingException {
        return userDao.findByEmail(email);
    }

    @Override
    public int save(User user) throws SQLException, NamingException {
        Pattern checkEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = checkEmail.matcher(user.getEmail());

        if(matcher.find()) {
            return userDao.save(user);
        }

        return 0;
    }

    @Override
    public void updateUser(User user) throws NamingException, SQLException {
        userDao.updateUser(user);
    }
}