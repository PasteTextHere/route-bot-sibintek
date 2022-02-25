package ru.sibbot.routebot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car implements CanBeInlineButton {

    public Car(String registrationNumberFull, CarModel carModel, int odometer, double lastFuel) {
        this.registrationNumberFull = registrationNumberFull;
        this.registrationNumberDigits = extractInt(this.registrationNumberFull);
        this.carModel = carModel;
        this.odometer = odometer;
        this.lastFuel = lastFuel;
    }

    /**
     * Госномер авто
     */
    @Id
    @NotNull
    private String registrationNumberFull;

    /**
     * Госномер авто только цифры
     */
    @NotNull
    private int registrationNumberDigits;

    /**
     * Пробег авто
     */
    @NotNull
    private int odometer;

    /**
     * остаток топлива
     */
    @NotNull
    private double lastFuel;

    /**
     * Модель автомобиля
     */
    @NotNull
    @OneToOne
    private CarModel carModel;

    @Override
    public String toString() {
        return "Автомобиль: {" + carModel.getModelName() +
                ", госномер: " + registrationNumberFull +
                ", пробег: " + odometer +
                ", остаток топлива: " + lastFuel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return registrationNumberDigits == car.registrationNumberDigits && odometer == car.odometer && Double.compare(car.lastFuel, lastFuel) == 0 && registrationNumberFull.equals(car.registrationNumberFull) && carModel.equals(car.carModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumberFull, registrationNumberDigits, carModel, odometer, lastFuel);
    }

    /**
     * Получить значение primary key для сущности
     */
    @Override
    public String getIdValueForButton() {
        return registrationNumberFull;
    }

    /**
     * Получить значение отобрадения кнопки для пользователя
     */
    @Override
    public String getNameForButton() {
        return this.carModel.getModelName() + " " + registrationNumberFull;
    }

    private int extractInt(String str) {
        return Integer.parseInt(str.replaceAll("[\\D]", ""));
    }
}
