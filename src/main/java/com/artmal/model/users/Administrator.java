package com.artmal.model.users;

/**
 * Simple Java Bean for representing {@link Administrator} entity.
 * @author Artem Malchenko
 */
public class Administrator {
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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassportSerialNumbers() {
        return passportSerialNumbers;
    }
    public void setPassportSerialNumbers(String passportSerialNumbers) {
        this.passportSerialNumbers = passportSerialNumbers;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public User getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}
