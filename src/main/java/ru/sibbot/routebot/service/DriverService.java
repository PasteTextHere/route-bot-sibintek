package ru.sibbot.routebot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibbot.routebot.model.Driver;
import ru.sibbot.routebot.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Autowired
    private DriverRepository driverRepository;

    /**
     * Добавить водителя
     */
    public void addDriver(Driver driver) {
        Driver addedDriver = driverRepository.save(driver);
        if (driver.equals(addedDriver)) {
            logger.info(driver + " was added");
        } else logger.warn(driver + "wasn't added");
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

    /**
     * Удалить водителя по ID
     */
    public Driver deleteDriver(long chatId) {
        Driver currentDriver = driverRepository.findById(chatId).get();
        driverRepository.deleteById(chatId);
        return currentDriver;
    }

    /**
     * Проверка существования водителя с данным chatID
     */
    public boolean driverExist(long telegramChatId) {
        return driverRepository.existsById(telegramChatId);
    }

    /**
     * Является ли пользователь администратором
     */
    public boolean isAdminUser(long telegramChatId) {
        Optional<Driver> driver = driverRepository.findById(telegramChatId);
        return driver.map(Driver::isAdmin).orElseGet(() -> driver.get().isAdmin());
    }
}
