package com.artmal.service;

import com.artmal.model.users.Dispatcher;
import com.artmal.utils.ValidationException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Additional layer for {@link com.artmal.dao.DispatcherDao}.
 * @author Artem Malchenko
 */
public interface DispatcherService {
    boolean save(Dispatcher dispatcher) throws SQLException, NamingException, ValidationException;

    Dispatcher findByUserId(long id) throws SQLException, NamingException;
    Dispatcher findById(long id) throws SQLException, NamingException;
    Set<Dispatcher> findAll() throws SQLException, NamingException;
}
