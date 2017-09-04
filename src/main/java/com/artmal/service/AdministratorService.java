package com.artmal.service;

import com.artmal.model.users.Administrator;

import java.sql.SQLException;

public interface AdministratorService {
    Administrator findByUserId(long id) throws SQLException;
}
