package ru.sibbot.routebot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sibbot.routebot.model.Driver;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {

    void deleteByNameAndLastNameAndPatronymic(String name, String lastName, String patronymic);
}
