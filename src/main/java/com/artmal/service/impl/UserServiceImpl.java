package com.artmal.service.impl;

import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.users.User;
import com.artmal.service.UserService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.validation.RegistrationValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {
    final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private static UserDao userDao = new UserDaoImpl();

    @Override
    public User findByEmail(String email) throws SQLException, NamingException {
        return userDao.findByEmail(email);
    }

    @Override
    public int save(User user) throws SQLException, NamingException {
        try {
            if(!RegistrationValidator.validateEmail(user.getEmail())){
                throw new ValidationException(user.getEmail());
            }
        } catch (ValidationException e) {
            logger.error(e);
        }

        return userDao.save(user);
    }

    @Override
    public void updateUser(User user) throws NamingException, SQLException {
        try {
            if(!RegistrationValidator.validateEmail(user.getEmail())){
                throw new ValidationException(user.getEmail());
            }
        } catch (ValidationException e) {
            logger.error(e);
        }

        userDao.updateUser(user);
    }
}