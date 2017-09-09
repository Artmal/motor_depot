package com.artmal.model.users;

/**
 * Simple Java Bean for representing {@link Dispatcher} entity.
 * @author Artem Malchenko
 */
public class Dispatcher {
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
    public int getSalaryInDollars() {
        return salaryInDollars;
    }
    public void setSalaryInDollars(int salaryInDollars) {
        this.salaryInDollars = salaryInDollars;
    }
    public User getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}
