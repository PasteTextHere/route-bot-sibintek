package ru.sibbot.routebot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibbot.routebot.model.Driver;
import ru.sibbot.routebot.repository.DriverRepository;

import java.util.List;

@Service
public class DriverService {

    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Autowired
    private DriverRepository driverRepository;

    /**
     * Добавить водителя
     */
    public Driver addDriver(Driver driver) {
        Driver addedDriver = driverRepository.save(driver);
        if (driver.equals(addedDriver)) {
            logger.info(driver + " was added");
            return addedDriver;
        } else logger.warn(driver + "wasn't added");
        return addedDriver;
    }

    /**
     * Получить список всех водителей
     */
    public List<Driver> getAllDrivers() {
        return (List<Driver>) driverRepository.findAll();
    }

    /**
     * Удалить водителя по ФИО
     */
    public void deleteDriver(String name, String lastName, String patronymic) {
        driverRepository.deleteByNameAndLastNameAndPatronymic(name, lastName, patronymic);
    }

    public boolean driverExist(long telegramChatId) {
        return driverRepository.existsById(telegramChatId);
    }


}
