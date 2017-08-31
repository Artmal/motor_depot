package com.artmal.model;

/**
 * Simple Java Bean for representing Car entity.
 * @author Artem Malchenko
 */
public class Car {
    private long id;
    private String registrationNumber;
    private String carType;
    private String manufacturer;
    private String model;
    private int productionYear;
    private int numberOfSeats;
    private String color;
    private int mileage;
    private String carCondition;

    public Car() {
    }

    public Car(long id, String registrationNumber, String carType, String manufacturer, String model,
               int productionYear, int numberOfSeats, String color, int mileage, String carCondition) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.carType = carType;
        this.manufacturer = manufacturer;
        this.model = model;
        this.productionYear = productionYear;
        this.numberOfSeats = numberOfSeats;
        this.color = color;
        this.mileage = mileage;
        this.carCondition = carCondition;
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
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getProductionYear() {
        return productionYear;
    }
    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
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
    public int getMileage() {
        return mileage;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    public String getCarCondition() {
        return carCondition;
    }
    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }
}