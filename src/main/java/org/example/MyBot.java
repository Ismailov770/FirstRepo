package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class MyBot extends TelegramLongPollingBot {

    // HI Ravil!
    @Override
    public String getBotUsername() {
        return "t.me/boom_ravil_bot";
    }

    @Override
    public String getBotToken() {
        return "5943343671:AAGzXdH9AnzNdwxPnruDj6L8igKKqXpIrAg";

    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            String callbackData = "";
            String callbackQueryId = "";

            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                callbackData = callbackQuery.getData();
                callbackQueryId = callbackQuery.getId();
            }

            if (messageText.equals("/start")) {
                start(chatId);

            } else if (callbackData.equals("/uzbek")) {
                uzbek(chatId);
            } else if (callbackData.equals("/russian")) {
                rus(chatId);
            } else {

            }
        }
    }

    public void uzbek(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("O'zbek tilini tanladingiz!");
        sendMessage(sendMessage);

    }

    public void rus(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Вы выбрали русский язык!");
        sendMessage(sendMessage);
    }

    public void start(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, how are you? Choose language");

        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Uzbek");
        button1.setCallbackData("/uzbek");
        row.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Russian");
        button2.setCallbackData("/russian");
        row.add(button2);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row);
        markup.setKeyboard(buttons);
        message.setReplyMarkup(markup);

        sendMessage(message);
    }

    public void sendMessage(SendMessage message) {

        try {
            execute(message); //Send the Message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

}
