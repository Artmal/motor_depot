package com.artmal.dao;

import com.artmal.model.users.Administrator;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Data Access Object for {@link Administrator}.
 * @author Artem Malchenko
 */
public interface AdministratorDao {
    Administrator findByUserId(long id) throws SQLException, NamingException;
}
