package com.artmal.utils;

import com.artmal.model.Car;
import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;
import com.artmal.service.CarService;
import com.artmal.service.impl.CarServiceImpl;
import com.artmal.service.impl.DriverServiceImpl;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class CarUtils {
    public static int typeToInt(CarType carType) {
        switch(carType) {
            case Van:
                return 1;
            case Minivan:
                return 2;
            case Campervan:
                return 3;
            case Mini_truck:
                return 4;
            case Truck:
                return 5;
            case Big_truck:
                return 6;
            case Micro:
                return 7;
            case Sedan:
                return 8;
            case CUV:
                return 9;
            case SUV:
                return 10;
            case Hatchback:
                return 11;
            case Roadster:
                return 12;
            case Pickup:
                return 13;
            case Coupe:
                return 14;
            case Supercar:
                return 15;
            case Cabriolet:
                return 16;
        }

        throw new NoSuchElementException();
    }

    public static CarType intToType(int carTypeId) {
        switch(carTypeId) {
            case 1:
                return CarType.Van;
            case 2:
                return CarType.Minivan;
            case 3:
                return CarType.Campervan;
            case 4:
                return CarType.Mini_truck;
            case 5:
                return CarType.Truck;
            case 6:
                return CarType.Big_truck;
            case 7:
                return CarType.Micro;
            case 8:
                return CarType.Sedan;
            case 9:
                return CarType.CUV;
            case 10:
                return CarType.SUV;
            case 11:
                return CarType.Hatchback;
            case 12:
                return CarType.Roadster;
            case 13:
                return CarType.Pickup;
            case 14:
                return CarType.Coupe;
            case 15:
                return CarType.Supercar;
            case 16:
                return CarType.Cabriolet;
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

    public static CarCondition intToCondition(int carConditionId) {
        switch (carConditionId) {
            case 1:
                return CarCondition.Broken;
            case 2:
                return CarCondition.Repairing;
            case 3:
                return CarCondition.Ready;
        }

        throw new NoSuchElementException();
    }

    public static void addNewCarAsDriver(HttpServletRequest req) throws SQLException, NamingException {
        String registrationNumber = req.getParameter("registration-number");
        CarType type = CarType.valueOf(req.getParameter("type"));
        CarCondition condition = CarCondition.valueOf(req.getParameter("condition"));
        String model = req.getParameter("model");
        int numberOfSeats = Integer.parseInt(req.getParameter("number-of-seats"));
        String carColor = req.getParameter("color");
        long ownerId = new DriverServiceImpl().findByUserId((Long) req.getSession().getAttribute("id")).getId();

        Car car = new Car(registrationNumber, type, condition, model, numberOfSeats, carColor, ownerId);
        CarService carService = new CarServiceImpl();
        try {
            carService.save(car);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void addNewCarAsAdmin(HttpServletRequest req) throws SQLException {
        String registrationNumber = req.getParameter("registration-number");
        CarType type = CarType.valueOf(req.getParameter("type"));
        CarCondition condition = CarCondition.valueOf(req.getParameter("condition"));
        String model = req.getParameter("model");
        int numberOfSeats = Integer.parseInt(req.getParameter("number-of-seats"));
        String carColor = req.getParameter("color");
        long ownerId = Long.parseLong(req.getParameter("owner_id"));

        Car car = new Car(registrationNumber, type, condition, model, numberOfSeats, carColor, ownerId);
        CarService carService = new CarServiceImpl();
        try {
            carService.save(car);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}