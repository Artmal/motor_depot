package com.artmal.model.enums;

public enum CarType {
    Van("Van"),
    Minivan("Minivan"),
    Campervan("Campervan"),
    Mini_truck("Mini truck"),
    Truck("Truck"),
    Big_truck("Big truck"),
    Micro("Micro"),
    Sedan("Sedan"),
    CUV("CUV"),
    SUV("SUV"),
    Hatchback("Hatchback"),
    Roadster("Roadster"),
    Pickup("Pickup"),
    Coupe("Coupe"),
    Supercar("Supercar"),
    Cabriolet("Cabriolet");

    private String displayName;

    CarType(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }
}
