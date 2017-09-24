package com.artmal.service.impl;

import com.artmal.dao.DriverDao;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.validation.RegistrationValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

@Service
@Log4j
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverDao driverDao;

    @Override
    public boolean save(Driver driver) throws SQLException, NamingException, ValidationException {
        if(!RegistrationValidator.validateFullName(driver.getName())) {
            throw new ValidationException(driver.getName());
        } else if(!RegistrationValidator.validateAge(String.valueOf(driver.getAge()))) {
            throw new ValidationException(String.valueOf(driver.getAge()));
        } else if(!RegistrationValidator.validatePhoneNumber(driver.getPhoneNumber())) {
            throw new ValidationException((driver.getPhoneNumber()));
        }

        return driverDao.save(driver);
    }

    @Override
    public Driver findByUserId(long id) throws SQLException, NamingException {
        return driverDao.findByUserId(id);
    }

    @Override
    public Set<Driver> findAll() throws SQLException, NamingException {
        return driverDao.findAll();
    }

    @Override
    public Driver findById(long id) throws SQLException, NamingException {
        return driverDao.findById(id);
    }
}
