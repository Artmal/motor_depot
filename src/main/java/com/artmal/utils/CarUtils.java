package com.artmal.utils;

import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;
import com.sun.deploy.net.HttpRequest;

import java.util.NoSuchElementException;

public class CarUtils {
    public static int typeToInt(CarType carType) {
        switch(carType) {
            case Van: return 1;
            case Minivan: return 2;
            case Campervan: return 3;
            case Mini_truck: return 4;
            case Truck: return 5;
            case Big_truck: return 6;
            case Micro: return 7;
            case Sedan: return 8;
            case CUV: return 9;
            case SUV: return 10;
            case Hatchback: return 11;
            case Roadster: return 12;
            case Pickup: return 13;
            case Coupe: return 14;
            case Supercar: return 15;
            case Cabriolet: return 16;
        }

        throw new NoSuchElementException();
    }

    public static int conditionTypeToInt(CarCondition carCondition) {
        switch (carCondition) {
            case Broken: return 1;
            case Repairing: return 2;
            case Ready: return 3;
        }

        throw new NoSuchElementException();
    }

    public static void addNewCar(HttpRequest req) {

    }
}