package ru.sibbot.routebot.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.sibbot.routebot.model.CanBeInlineButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface CreateButtonsList {

    default List<List<InlineKeyboardButton>> createInlineKeyboardWithButtons(int weight, List<? extends CanBeInlineButton> list) {
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        for (var listObject : list) {
            buttonsRow.add(getButton(listObject.getNameForButton(), listObject.getIdValueForButton()));
            if (buttonsRow.size() == weight) {
                allButtons.add(List.copyOf(buttonsRow));
                buttonsRow.clear();
            }
        }
        if (!buttonsRow.isEmpty()) {
            allButtons.add(buttonsRow);
        }
        return allButtons;
    }

    private InlineKeyboardButton getButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
