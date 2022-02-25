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

    @Id
    @GeneratedValue
    private int id;

    private Date date;

    @OneToOne
    private Driver driver;

    @OneToOne
    private Car car;

    @OneToMany
    private List<Point> visitedPointsList;

//    private double
}
