package ru.sibbot.routebot.model;

import lombok.*;

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
public class Driver implements CanBeInlineButton {

    public Driver(long telegramChatId, String name, String lastName, String patronymic) {
        this.telegramChatId = telegramChatId;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

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

    /**
     * Механик
     */
    @NotNull
    private boolean isAdmin = false;

    @Override
    public String toString() {
        String isAdmin;
        if (this.isAdmin)
            isAdmin = "Механик";
        else
            isAdmin = "Водитель";
        return isAdmin + " {" +
                "Id=" + telegramChatId +
                ", " + lastName +
                ", " + name +
                ", " + patronymic +
                '}';
    }

    /**
     * Получить значение primary key для сущности
     */
    @Override
    public String getIdValueForButton() {
        return String.valueOf(this.telegramChatId);
    }

    /**
     * Получить значение отображения кнопки для пользователя
     */
    @Override
    public String getNameForButton() {
        return lastName + " " + name.charAt(0) + " " + patronymic.charAt(0);
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
