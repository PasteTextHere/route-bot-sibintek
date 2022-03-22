package ru.sibbot.routebot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "driving_event")
@AllArgsConstructor
@NoArgsConstructor
public class WorkShift {

    /**
     * Id события для сохранения в БД
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * Дата события
     */
    private Date date;

    /**
     * Водитель
     */
    @OneToOne
    private Driver driver;

    /**
     * Автомобиль
     */
    @OneToOne
    private Car car;

    /**
     * Лист посещенных точек
     */
    @OneToMany
    private List<Point> visitedPointsList;

    /**
     * Пробег в городе
     */
    private int cityDistance;

    /**
     * Пробег за городом
     */
    private int outCityDistance;

    /**
     * Пробег общий
     */
    private int totalDistance;

    /**
     * Расход в городе
     */
    private double usedFuelCity;

    /**
     * Расход за городом
     */
    private double usedFuelOutCity;

    /**
     * Общий расход
     */
    private double usedFuelTotal;


}
