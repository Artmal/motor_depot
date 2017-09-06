package com.artmal.service;

import com.artmal.model.users.Dispatcher;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

public interface DispatcherService {
    boolean save(Dispatcher dispatcher) throws SQLException, NamingException;

    Dispatcher findByUserId(long id) throws SQLException, NamingException;
    Dispatcher findById(long id) throws SQLException, NamingException;
    Set<Dispatcher> findAll() throws SQLException, NamingException;
}
