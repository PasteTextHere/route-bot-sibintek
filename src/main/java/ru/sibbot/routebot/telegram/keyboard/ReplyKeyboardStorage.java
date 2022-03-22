package ru.sibbot.routebot.telegram.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
public class ReplyKeyboardStorage {

    public ReplyKeyboardMarkup getMainKeyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Settings");
        keyboardRow.add("Calculate");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(keyboardRow));
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getUserSettingsKeyboard() {
        KeyboardRow backRow = new KeyboardRow();
        backRow.add("Return back");
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Delete me");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(keyboardRow, backRow));
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getAdminSettingsKeyboard() {
        KeyboardRow backRow = new KeyboardRow();
        backRow.add("Return back");
        KeyboardRow keyboardRowUsers = new KeyboardRow();
        keyboardRowUsers.add("Admins"); //вывести список админов
        keyboardRowUsers.add("Users"); //вывести список юзеров
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Points"); //меню работы с точками
        keyboardRow.add("Cars"); //вывести список авто
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(keyboardRowUsers, keyboardRow, backRow));
        return replyKeyboardMarkup;
    }
}
