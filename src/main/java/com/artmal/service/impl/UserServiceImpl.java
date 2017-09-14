package com.artmal.service.impl;

import com.artmal.dao.UserDao;
import com.artmal.dao.impl.UserDaoImpl;
import com.artmal.model.users.User;
import com.artmal.service.UserService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.validation.RegistrationValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

@Service
@Log4j
public class UserServiceImpl implements UserService {
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
            log.error(e);
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
            log.error(e);
        }

        userDao.updateUser(user);
    }
}