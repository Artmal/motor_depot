package com.artmal.model;

import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;

/**
 * Drivers have cars. They manage cars in their garages(/driver-dashboard/garage).
 * @author Artem Malchenko
 */
public class Car {
    private long id;
    private String registrationNumber;
    private CarType type;
    private CarCondition condition;
    private String model;
    private int numberOfSeats;
    private String color;
    private long ownerId;

    public Car() {
    }

    public Car(String registrationNumber, CarType type, CarCondition condition, String model, int numberOfSeats, String color, long ownerId) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.condition = condition;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.color = color;
        this.ownerId = ownerId;
    }

    public Car(long id, String registrationNumber, CarType type, CarCondition condition, String model, int numberOfSeats, String color) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.condition = condition;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.color = color;
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
