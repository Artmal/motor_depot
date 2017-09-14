package com.artmal.service.impl;

import com.artmal.dao.DispatcherDao;
import com.artmal.dao.impl.DispatcherDaoImpl;
import com.artmal.model.users.Dispatcher;
import com.artmal.service.DispatcherService;
import com.artmal.utils.ValidationException;
import com.artmal.utils.validation.RegistrationValidator;
import com.artmal.utils.validation.TripValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Set;

@Service
@Log4j
public class DispatcherServiceImpl implements DispatcherService {
    private static DispatcherDao dispatcherDao = new DispatcherDaoImpl();

    @Override
    public boolean save(Dispatcher dispatcher) throws SQLException, NamingException {
        try {
            if(!RegistrationValidator.validateEmail(dispatcher.getUserInfo().getEmail())) {
                throw new ValidationException(dispatcher.getUserInfo().getEmail());
            } else if(!RegistrationValidator.validateFullName(dispatcher.getName())) {
                throw new ValidationException(dispatcher.getName());
            } else if(!RegistrationValidator.validatePhoneNumber(dispatcher.getPhoneNumber())) {
                throw new ValidationException(dispatcher.getPhoneNumber());
            } else if(!TripValidator.validatePayment(String.valueOf(dispatcher.getSalaryInDollars()))) {
                throw new ValidationException(String.valueOf(dispatcher.getSalaryInDollars()));
            }
        } catch (ValidationException e) {
            log.error(e);
        }

        return dispatcherDao.save(dispatcher);
    }

    @Override
    public Dispatcher findByUserId(long id) throws SQLException, NamingException {
        return dispatcherDao.findByUserId(id);
    }

    @Override
    public Dispatcher findById(long id) throws SQLException, NamingException {
        return dispatcherDao.findById(id);
    }

    @Override
    public Set<Dispatcher> findAll() throws SQLException, NamingException {
        return dispatcherDao.findAll();
    }
}
