package ru.sibbot.routebot.model;

public interface CanBeInlineButton {
    
    /**
     * Получить значение primary key для сущности
     */
    String getIdValueForButton();

    /**
     * Получить значение отобрадения кнопки для пользователя
     */
    String getNameForButton();
}
