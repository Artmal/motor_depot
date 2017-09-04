package com.artmal.model;

import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;

public class Car {
    private long id;
    private String registrationNumber;
    private String model;
    private int numberOfSeats;
    private String color;
    private CarType type;
    private CarCondition condition;
    private long ownerId;

    public Car() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public CarType getType() {
        return type;
    }
    public void setType(CarType type) {
        this.type = type;
    }
    public CarCondition getCondition() {
        return condition;
    }
    public void setCondition(CarCondition condition) {
        this.condition = condition;
    }
    public long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
