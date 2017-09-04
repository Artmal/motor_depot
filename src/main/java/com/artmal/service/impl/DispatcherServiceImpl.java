package com.artmal.service.impl;

import com.artmal.dao.DispatcherDao;
import com.artmal.dao.impl.DispatcherDaoImpl;
import com.artmal.model.users.Dispatcher;
import com.artmal.service.DispatcherService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

public class DispatcherServiceImpl implements DispatcherService {
    private static DispatcherDao dispatcherDao = new DispatcherDaoImpl();

    @Override
    public boolean save(Dispatcher dispatcher) throws SQLException, NamingException {
        return dispatcherDao.save(dispatcher);
    }

    @Override
    public Dispatcher findById(long id) throws SQLException {
        return dispatcherDao.findById(id);
    }

    @Override
    public Set<Dispatcher> findAll() throws SQLException {
        return dispatcherDao.findAll();
    }
}
