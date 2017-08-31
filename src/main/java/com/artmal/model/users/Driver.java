package com.artmal.model.users;

import java.util.Date;

/**
 * Simple Java Bean for representing Driver entity.
 * @author Artem Malchenko
 */
public class Driver {
    private int id;
    private String name;
    private String passportSerialNumber;
    private String phoneNumber;
    private int yearsOfExperience;
    private Date dateOfBirth;
    private long userId;

    public Driver() {
    }

    public Driver(String name, String passportSerialNumber, String phoneNumber, int yearsOfExperience, Date dateOfBirth, long userId) {
        this.name = name;
        this.passportSerialNumber = passportSerialNumber;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }

    public Driver(int id, String name, String passportSerialNumber, String phoneNumber,
                  int yearsOfExperience, Date dateOfBirth, int userId) {
        this.id = id;
        this.name = name;
        this.passportSerialNumber = passportSerialNumber;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassportSerialNumber() {
        return passportSerialNumber;
    }
    public void setPassportSerialNumber(String passportSerialNumber) {
        this.passportSerialNumber = passportSerialNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
}