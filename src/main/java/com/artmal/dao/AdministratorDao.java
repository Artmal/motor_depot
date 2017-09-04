package com.artmal.dao;

import com.artmal.model.users.Administrator;

import java.sql.SQLException;

/**
 * Data Access Object for Administrator Entity.
 * @author Artem Malchenko
 */
public interface AdministratorDao {
    Administrator findByUserId(long id) throws SQLException;
}
