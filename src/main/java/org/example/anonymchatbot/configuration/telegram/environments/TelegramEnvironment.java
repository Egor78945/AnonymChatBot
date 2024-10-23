package org.example.anonymchatbot.configuration.telegram.environments;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Schema(title = "Детали для telegram-api", description = "Содержит нужные компоненты для инициализации работы telegram-api")
@Configuration
public class TelegramEnvironment {
    private final String TELEGRAM_BOT_NAME;
    private final String TELEGRAM_BOT_TOKEN;

    public TelegramEnvironment(@Value("${telegram.bot.name}") String TELEGRAM_BOT_NAME, @Value("${telegram.bot.token}") String TELEGRAM_BOT_TOKEN) {
        this.TELEGRAM_BOT_NAME = TELEGRAM_BOT_NAME;
        this.TELEGRAM_BOT_TOKEN = TELEGRAM_BOT_TOKEN;
    }

    public String getTELEGRAM_BOT_NAME() {
        return TELEGRAM_BOT_NAME;
    }

    public String getTELEGRAM_BOT_TOKEN() {
        return TELEGRAM_BOT_TOKEN;
    }
}
