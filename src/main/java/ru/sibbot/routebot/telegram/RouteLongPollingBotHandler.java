package ru.sibbot.routebot.telegram;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sibbot.routebot.model.Car;
import ru.sibbot.routebot.model.CarModel;
import ru.sibbot.routebot.model.Driver;
import ru.sibbot.routebot.service.CarModelService;
import ru.sibbot.routebot.service.CarService;
import ru.sibbot.routebot.service.DriverService;
import ru.sibbot.routebot.telegram.keyboard.InlineKeyboardsStorage;

import java.util.*;

@Getter
@Component
public class RouteLongPollingBotHandler extends TelegramLongPollingBot {

    @Autowired
    private DriverService driverService;
    @Autowired
    private CarService carService;
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private InlineKeyboardsStorage inlineKeyboardsStorage;

    private final Map<Long, Integer> userRegistrationStepMap = new HashMap<>();
    private final Map<Long, Driver> userRegistrationCreateDriverMap = new HashMap<>();

    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.username}")
    private String botUsername;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (Objects.equals(update.getMessage().getText(), "123")) {
                CarModel carmodel = carModelService.addCarModel(new CarModel("default", 10.0, 9.5, 11, 10.5));
                for (int i = 0; i < 7; i++) {
                    carService.addCar(new Car("fg"+ i, carmodel, 5645, 6.7));
                }
            }
            try {
                Message message = update.getMessage();
                if (message.hasText())
                    messageHandler(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void messageHandler(Message message) throws TelegramApiException {
//        if (!driverService.driverExist(message.getChatId())) {
//            driverAddingProcess(message);
//        }
        sendMessage(message.getChatId(), "hi from message handler inline", inlineKeyboardsStorage.getAllCarsList(carService));
//        sendMessage(message.getChatId(), "hi from message handler reply", defaultKeyboard);
    }

    private synchronized void driverAddingProcess(Message message) throws TelegramApiException {
        long chatId = message.getChatId();
        if (!userRegistrationStepMap.containsKey(chatId) || !userRegistrationCreateDriverMap.containsKey(chatId)) {
            userRegistrationStepMap.put(chatId, 0);
            userRegistrationCreateDriverMap.put(chatId, new Driver());
        }
        if (userRegistrationStepMap.containsKey(chatId) && userRegistrationStepMap.get(chatId) != 4) {
            switch (userRegistrationStepMap.get(chatId)) {
                case 0:
                    userRegistrationCreateDriverMap.get(chatId).setTelegramChatId(chatId);
                    sendMessage(chatId, "Введи свою фамилию:");
                    userRegistrationStepMap.replace(chatId, 1);
                    break;
                case 1:
                    userRegistrationCreateDriverMap.get(chatId).setLastName(message.getText());
                    sendMessage(chatId, "Введи свое имя:");
                    userRegistrationStepMap.replace(chatId, 2);
                    break;
                case 2:
                    userRegistrationCreateDriverMap.get(chatId).setName(message.getText());
                    sendMessage(chatId, "Введи свое отчество:");
                    userRegistrationStepMap.replace(chatId, 3);
                    break;
                case 3:
                    userRegistrationCreateDriverMap.get(chatId).setPatronymic(message.getText());
                    driverService.addDriver(userRegistrationCreateDriverMap.get(chatId));
                    userRegistrationStepMap.replace(chatId, 4);
                    break;
            }
        }
    }

    private void sendMessage(Long chatId, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        execute(sendMessage);
    }

    private void sendMessage(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
    }

    private void sendMessage(Long chatId, String text, ReplyKeyboardMarkup replyKeyboardMarkup) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        execute(sendMessage);
    }
}