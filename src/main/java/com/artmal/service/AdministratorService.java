package com.artmal.service;

import com.artmal.model.users.Administrator;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface AdministratorService {
    Administrator findByUserId(long id) throws SQLException, NamingException;
}
