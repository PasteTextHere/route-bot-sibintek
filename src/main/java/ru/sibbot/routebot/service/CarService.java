package ru.sibbot.routebot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibbot.routebot.model.Car;
import ru.sibbot.routebot.repository.CarRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarRepository carRepository;

    /**
     * Добавить автомобиль в БД
     */
    public Car addCar(Car car) {
        logger.info(car + " was added");
        return carRepository.save(car);
    }

    /**
     * Добавить список автомобилей в БД
     */
    public Iterable<Car> addAllCars(Iterable<Car> cars) {
        logger.info(cars + " was added");
        return carRepository.saveAll(cars);
    }

    public String getFullCarNumber(int numberDigits) {
        List<Car> currentCar = getCar(numberDigits);
        if (currentCar.size() == 1) {
            return currentCar.get(0).getRegistrationNumberFull();
        } else return currentCar.stream().map(Car::getRegistrationNumberFull).collect(Collectors.toList()).toString();
    }

    /**
     * Поличить автомобиль по полному госномеру
     */
    public Car getCar(String carFullNumber) {
        var foundCar = carRepository.findById(carFullNumber);
        if (foundCar.isPresent()) {
            return foundCar.get();
        } else {
            logger.warn("Can't find car with number " + carFullNumber);
            return null;
        }
    }

    /**
     * Получить список автомобилей по цифрам в госномере
     */
    public List<Car> getCar(int numberDigits) {
        var foundCar = carRepository.findCarByRegistrationNumberDigits(numberDigits);
        if (!foundCar.isEmpty()) {
            return foundCar;
        } else {
            logger.warn("Can't find car with number " + numberDigits);
            return Collections.emptyList();
        }
    }

    /**
     * Получить список всех автомобилей
     */
    public List<Car> getAllCars() {
        return ((List<Car>) carRepository.findAll());
    }


    /**
     * Изменить показания одометра
     */
    public void changeOdometer(String fullNumber, int odometer) {
        carRepository.updateOdometer(fullNumber, odometer);
    }

    public void changeOdometer(int numberDigits, int odometer) {
        carRepository.updateOdometer(numberDigits, odometer);
    }
}
