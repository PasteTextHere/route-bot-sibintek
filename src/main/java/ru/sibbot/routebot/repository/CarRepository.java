package ru.sibbot.routebot.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibbot.routebot.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {
    List<Car> findCarByRegistrationNumberDigits(int numberDigits);

    @Modifying
    @Query("update Car c set c.odometer = :odometer where c.registrationNumberFull = :fullNumber")
    void updateOdometer(@Param("fullNumber") String fullNumber, @Param("odometer") int odometer);

    @Modifying
    @Query("update Car c set c.odometer = :odometer where c.registrationNumberDigits = :numberDigits")
    void updateOdometer(@Param("numberDigits") int numberDigits, @Param("odometer") int odometer);
}
