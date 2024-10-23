package org.example.anonymchatbot.configuration.telegram.initializer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.service.telegram.bot.AnonymChatBot;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Schema(title = "Коннектор телеграмм-бота", description = "Связывает телеграмм бота и приложение")
@Configuration
@RequiredArgsConstructor
public class TelegramBotInitializer {
    private final TelegramBotsApi telegramBotsApi;
    private final AnonymChatBot anonymChatBot;

    @PostConstruct
    private void connect() throws TelegramApiException {
        telegramBotsApi.registerBot(anonymChatBot);
    }
}
