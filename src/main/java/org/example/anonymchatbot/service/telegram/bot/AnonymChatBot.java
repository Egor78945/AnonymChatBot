package org.example.anonymchatbot.service.telegram.bot;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.anonymchatbot.configuration.telegram.environment.TelegramEnvironment;
import org.example.anonymchatbot.service.telegram.controller.MessageControllerImplementation;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;

@Schema(title = "Анонимный чат - бот", description = "Класс, принимающий все сообщения, отправленные телеграмм-боту")
@Configuration
public class AnonymChatBot extends TelegramLongPollingBot {
    private final TelegramEnvironment telegramEnvironment;
    private final MessageControllerImplementation messageControllerImplementation;

    public AnonymChatBot(TelegramEnvironment telegramEnvironment, MessageControllerImplementation messageControllerImplementation) {
        super(telegramEnvironment.getTELEGRAM_BOT_TOKEN());
        this.telegramEnvironment = telegramEnvironment;
        this.messageControllerImplementation = messageControllerImplementation;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        try {
            List<BotApiMethod<? extends Serializable>> result = messageControllerImplementation.receive(message);
            for (BotApiMethod<? extends Serializable> m : result) {
                sendMessage(m);
            }
        } catch (Exception e) {
            try {
                sendApiMethod(new SendMessage(chatId.toString(), "Unknown command."));
            } catch (TelegramApiException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return telegramEnvironment.getTELEGRAM_BOT_NAME();
    }

    public void sendMessage(BotApiMethod<? extends Serializable> message) throws TelegramApiException {
        sendApiMethod(message);
    }
}
