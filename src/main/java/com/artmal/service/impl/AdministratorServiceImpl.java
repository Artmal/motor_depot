package com.artmal.service.impl;

import com.artmal.dao.AdministratorDao;
import com.artmal.dao.impl.AdministratorDaoImpl;
import com.artmal.model.users.Administrator;
import com.artmal.service.AdministratorService;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private static AdministratorDao administratorDao = new AdministratorDaoImpl();

    @Override
    public Administrator findByUserId(long id) throws SQLException, NamingException {
        return administratorDao.findByUserId(id);
    }
}
