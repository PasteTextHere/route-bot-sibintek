package ru.sibbot.routebot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    /**
     * Идентификатор пользователя телеграм
     */
    @Id
    @NotNull
    private long telegramChatId;

    /**
     * Имя
     */
    @NotNull
    private String name;

    /**
     * Фамилия
     */
    @NotNull
    private String lastName;

    /**
     * Отчество
     */
    @NotNull
    private String patronymic;

    @Override
    public String toString() {
        return "Driver{" +
                "Id=" + telegramChatId +
                ", " + lastName +
                ", " + name +
                ", " + patronymic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return telegramChatId == driver.telegramChatId && name.equals(driver.name) && lastName.equals(driver.lastName) && patronymic.equals(driver.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telegramChatId, name, lastName, patronymic);
    }
}
