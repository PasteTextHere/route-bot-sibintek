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
@Table(name = "points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    @Id
    @NotNull
    private String pointName;

    @NotNull
    private double altitude;

    @NotNull
    private double longitude;

    @NotNull
    private String address;

    @Override
    public String toString() {
        return  pointName;
    }
}
