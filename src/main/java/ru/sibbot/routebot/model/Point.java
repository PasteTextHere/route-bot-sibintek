package ru.sibbot.routebot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "points")
@Getter
@Setter
@NoArgsConstructor
public class Point implements CanBeInlineButton {

    public Point(String pointName, double altitude, double longitude, String address, int outCityDistance) {
        this.pointName = pointName;
        this.altitude = altitude;
        this.longitude = longitude;
        this.address = address;
        this.outCityDistance = outCityDistance;
        this.isCity = this.outCityDistance != 0;
    }

    public Point(String pointName, double altitude, double longitude, String address, boolean isCity, int outCityDistance) {
        this.pointName = pointName;
        this.altitude = altitude;
        this.longitude = longitude;
        this.address = address;
        this.outCityDistance = outCityDistance;
        this.isCity = this.outCityDistance != 0;
    }

    /**
     * Название точки
     */
    @Id
    @NotNull
    private String pointName;

    /**
     * Широта
     */
    @NotNull
    private double altitude;

    /**
     * Долгота
     */
    @NotNull
    private double longitude;

    /**
     * Адрес
     */
    private String address;

    /**
     * Является ли точка загородной
     */
    @NotNull
    private boolean isCity;

    /**
     * Удаленность точки от МКАД
     */
    @NotNull
    private int outCityDistance = 0;

    /**
     * Получить значение primary key для сущности
     */
    @Override
    public String getIdValueForButton() {
        return this.pointName;
    }

    /**
     * Получить значение отображения кнопки для пользователя
     */
    @Override
    public String getNameForButton() {
        return this.pointName;
    }

    @Override
    public String toString() {
        return pointName;
    }
}
