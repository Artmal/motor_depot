package com.artmal.model;

import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;
import lombok.Data;

/**
 * Drivers have cars. They manage cars in their garages(/driver-dashboard/garage).
 * @author Artem Malchenko
 */
public @Data class Car {
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
}
