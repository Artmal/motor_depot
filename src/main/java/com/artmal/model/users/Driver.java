package com.artmal.model.users;

/**
 * Simple Java Bean for representing {@link Driver} entity.
 */
public class Driver {
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
    public void setPassportSerialNumbers(String passport_serial_numbers) {
        this.passportSerialNumbers = passport_serial_numbers;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public User getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}
