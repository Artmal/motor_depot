package com.artmal.model.users;

import lombok.Data;

/**
 * Simple Java Bean for representing {@link Dispatcher} entity.
 * @author Artem Malchenko
 */
public @Data class Dispatcher {
    private long id;
    private String name;
    private String passportSerialNumbers;
    private String phoneNumber;
    private int salaryInDollars;

    private User userInfo;

    public Dispatcher() {
    }

    public Dispatcher(long id, String name, String passportSerialNumbers, String phoneNumber, int salaryInDollars, User userInfo) {
        this.id = id;
        this.name = name;
        this.passportSerialNumbers = passportSerialNumbers;
        this.phoneNumber = phoneNumber;
        this.salaryInDollars = salaryInDollars;
        this.userInfo = userInfo;
    }

    public Dispatcher(String name, String passportSerialNumbers, String phoneNumber, int salaryInDollars, User userInfo) {
        this.name = name;
        this.passportSerialNumbers = passportSerialNumbers;
        this.phoneNumber = phoneNumber;
        this.salaryInDollars = salaryInDollars;
        this.userInfo = userInfo;
    }
}
