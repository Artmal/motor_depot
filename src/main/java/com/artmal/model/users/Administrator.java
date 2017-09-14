package com.artmal.model.users;

import lombok.Data;

/**
 * Simple Java Bean for representing {@link Administrator} entity.
 * @author Artem Malchenko
 */
public @Data class Administrator {
    private long id;
    private String name;
    private String passportSerialNumbers;
    private String phoneNumber;

    private User userInfo;

    public Administrator() {
    }

    public Administrator(long id, String name, String passportSerialNumbers, String phoneNumber, User userInfo) {
        this.id = id;
        this.name = name;
        this.passportSerialNumbers = passportSerialNumbers;
        this.phoneNumber = phoneNumber;
        this.userInfo = userInfo;
    }

    public Administrator(String name, String passportSerialNumbers, String phoneNumber, User userInfo) {
        this.name = name;
        this.passportSerialNumbers = passportSerialNumbers;
        this.phoneNumber = phoneNumber;
        this.userInfo = userInfo;
    }
}
