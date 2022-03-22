package ru.sibbot.routebot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sibbot.routebot.model.Point;

public interface PointRepository extends CrudRepository<Point, String> {
}
