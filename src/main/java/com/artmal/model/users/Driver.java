package com.artmal.model.users;

import lombok.Data;

/**
 * Simple Java Bean for representing {@link Driver} entity.
 */
public @Data class Driver {
    private long id;
    private String name;
    private String passportSerialNumbers;
    private String phoneNumber;
    private int age;

    private User userInfo;

    public Driver() {
    }

    public Driver(long id, String name, String passportSerialNumbers, String phoneNumber, int age, User userInfo) {
        this.id = id;
        this.name = name;
        this.passportSerialNumbers = passportSerialNumbers;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.userInfo = userInfo;
    }

    public Driver(String name, String passportSerialNumbers, String phoneNumber, int age, User userInfo) {
        this.name = name;
        this.passportSerialNumbers = passportSerialNumbers;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.userInfo = userInfo;
    }
}
