package com.artmal.utils;

import com.artmal.model.enums.Role;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * All utility methods for working with db.
 * @author Artem Malchenko
 */
@Component
@Log4j
public final class DatabaseUtils {
    public static DataSource dataSource;

    private DatabaseUtils() { }


    /**
     * In database there is the dictionary called roles(id, name), so we
     * need conversion between Role and int in order to save entry to database.
     * @return id in 'roles' table.
     */
    public static Role intToRole(int valueFromDB) {
        switch(valueFromDB) {
            case 1:
                return Role.Driver;
            case 2:
                return Role.Dispatcher;
            case 3:
                return Role.Admin;
        }

        return null;
    }

    public static DataSource initializeDataSource() {
        if(dataSource == null) {
            try {
                Context ctx = new InitialContext();
                Context envContext = (Context) ctx.lookup("java:comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
                return dataSource;
            } catch (NamingException e) {
                log.error(e);
            }
        }

        return dataSource;
    }
}