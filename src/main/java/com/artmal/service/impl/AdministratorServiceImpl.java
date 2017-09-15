package com.artmal.service.impl;

import com.artmal.dao.AdministratorDao;
import com.artmal.model.users.Administrator;
import com.artmal.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public Administrator findByUserId(long id) throws SQLException, NamingException {
        return administratorDao.findByUserId(id);
    }
}
