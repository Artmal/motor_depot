package com.artmal.utils;

import com.artmal.model.enums.Role;

/**
 * All utility methods for working with db.
 * @author Artem Malchenko
 */
public final class DatabaseUtils {
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
}