package com.artmal.utils;

import com.artmal.model.enums.Role;

public class DatabaseUtils {
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
