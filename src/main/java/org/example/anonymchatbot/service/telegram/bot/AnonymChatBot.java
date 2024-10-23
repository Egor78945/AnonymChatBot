package org.example.anonymchatbot.service.telegram.bot;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.anonymchatbot.configuration.telegram.environments.TelegramEnvironment;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Schema(title = "Анонимный чат - бот", description = "Класс, принимающий все сообщения, отправленные телеграмм-боту")
@Configuration
public class AnonymChatBot extends TelegramLongPollingBot {
    private final TelegramEnvironment telegramEnvironment;

    public AnonymChatBot(TelegramEnvironment telegramEnvironment) {
        super(telegramEnvironment.getTELEGRAM_BOT_TOKEN());
        this.telegramEnvironment = telegramEnvironment;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    @Override
    public String getBotUsername() {
        return telegramEnvironment.getTELEGRAM_BOT_NAME();
    }
}
