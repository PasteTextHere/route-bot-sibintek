package ru.sibbot.routebot.telegram.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.sibbot.routebot.model.Car;
import ru.sibbot.routebot.service.CarService;

import java.util.List;

@Component
public class InlineKeyboardsStorage implements CreateButtonsList{

    public InlineKeyboardMarkup getAllCarsList(CarService carService) {
        List<Car> cars = carService.getAllCars();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(createInlineKeyboardWithButtons(2, cars));
        return inlineKeyboardMarkup;
    }
}
