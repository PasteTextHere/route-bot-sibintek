package ru.sibbot.routebot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sibbot.routebot.model.CarModel;

public interface CarModelRepository extends CrudRepository<CarModel, String> {
}
