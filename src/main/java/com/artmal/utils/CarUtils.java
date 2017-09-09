package com.artmal.utils;

import com.artmal.model.Car;
import com.artmal.model.enums.CarCondition;
import com.artmal.model.enums.CarType;
import com.artmal.service.CarService;
import com.artmal.service.impl.CarServiceImpl;
import com.artmal.service.impl.DriverServiceImpl;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * Utility class which is used for operations with
 * {@link Car} objects.
 * @author Artem Malchenko
 */
public final class CarUtils {
    final static Logger logger = Logger.getLogger(CarUtils.class);

    private CarUtils() { }

    /**
     * In database there is the dictionary called car_types(id, name), so we
     * need conversion between CarType and int in order to save entry to database.
     * @return id in 'car_types' table.
     */
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

    /**
     * In database there is the dictionary called car_types(id, name), so we
     * need conversion between int and Car Type in order to fetch entry from database.
     * @return {@link CarType} which corresponds carTypeId in car_types.
     */
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

    /**
     * In database there is the dictionary called car_condition_types(id, name), so we
     * need conversion between CarCondition and int in order to save entry to database.
     * @return id in 'car_condition_types' table.
     */
    public static int conditionTypeToInt(CarCondition carCondition) {
        switch (carCondition) {
            case Broken: return 1;
            case Repairing: return 2;
            case Ready: return 3;
        }

        throw new NoSuchElementException();
    }

    /**
     * In database there is the dictionary called car_condition_types(id, name), so we
     * need conversion between int and CarCondition in order to fetch entry from database.
     * @return {@link CarCondition} which corresponds carConditionId in car_condition_types.
     */
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

    /**
     * Separate method for more clear code in servlet part.
     */
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
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
    }

    /**
     * Separate method for more clear code in servlet part.
     */
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
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
    }

    /**
     * @param cars take result set. In loop we always will get different objects from db.
     * @return constructed {@link Car}.
     */
    public static Car initializeCar(ResultSet cars) throws SQLException {
        Car car = new Car();
        car.setId(cars.getLong("id"));
        car.setRegistrationNumber(cars.getString("registration_number"));
        car.setType(CarUtils.intToType(cars.getInt("type_id")));
        car.setCondition(CarUtils.intToCondition(cars.getInt("condition_type_id")));
        car.setModel(cars.getString("model"));
        car.setNumberOfSeats(cars.getInt("number_of_seats"));
        car.setColor(cars.getString("color"));
        car.setOwnerId(cars.getLong("owner_id"));
        return car;
    }
}