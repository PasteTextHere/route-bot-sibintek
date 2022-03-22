package ru.sibbot.routebot.telegram.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.sibbot.routebot.model.CanBeInlineButton;
import ru.sibbot.routebot.model.Car;
import ru.sibbot.routebot.service.CarService;

import java.util.List;

@Component
public class InlineKeyboardsStorage implements CreateButtonsList{

    public InlineKeyboardMarkup getAllCarsKeyboard(CarService carService) {
        List<Car> cars = carService.getAllCars();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(createInlineKeyboardWithButtons(2, cars));
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getKeyboardFromList(List<? extends CanBeInlineButton> buttonSource, int columnNumber) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(createInlineKeyboardWithButtons(columnNumber, buttonSource));
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getKeyboardFromList(List<? extends CanBeInlineButton> buttonSource) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(createInlineKeyboardWithButtons(1, buttonSource));
        return inlineKeyboardMarkup;
    }
}