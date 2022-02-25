package ru.sibbot.routebot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sibbot.routebot.model.CarModel;
import ru.sibbot.routebot.repository.CarModelRepository;

@Component
public class CarModelService {

    private static final Logger logger = LoggerFactory.getLogger(CarModelService.class);

    @Autowired
    private CarModelRepository carModelRepository;

    public CarModel addCarModel(CarModel carModel) {
        logger.info(carModel + " was added");
        return carModelRepository.save(carModel);
    }
}
