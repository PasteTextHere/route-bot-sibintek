package ru.sibbot.routebot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "models")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {

    /**
     * Название модели автомобиля
     */
    @Id
    @NotNull
    private String modelName;

    /**
     * Расход лето город
     */
    @NotNull
    private double fuelSummerCity;

    /**
     * Расход лето трасса
     */
    @NotNull
    private double fuelSummerOutCity;

    /**
     * Расход зима город
     */
    @NotNull
    private double fuelWinterCity;

    /**
     * Расход зима загород
     */
    @NotNull
    private double fuelWinterOutCity;

    /**
     * Получить название модели автомобиля
     */
    public String getModelName() {
        return this.modelName;
    }
    
    @Override
    public String toString() {
        return "CarModel{" +
                modelName +
                ", fuelSummerCity=" + fuelSummerCity +
                ", fuelSummerOutCity=" + fuelSummerOutCity +
                ", fuelWinterCity=" + fuelWinterCity +
                ", fuelWinterOutCity=" + fuelWinterOutCity +
                '}';
    }

}
