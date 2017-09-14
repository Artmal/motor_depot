package com.artmal.service.impl;

import com.artmal.dao.DriverDao;
import com.artmal.dao.impl.DriverDaoImpl;
import com.artmal.model.users.Driver;
import com.artmal.service.DriverService;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

@Service
public class DriverServiceImpl implements DriverService {
    private static DriverDao driverDao = new DriverDaoImpl();

    @Override
    public boolean save(Driver driver) throws SQLException, NamingException {
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
