package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Schema(title = "Контроллер сообщений", description = "Интерфейс представляет контроллер сообщений")
@Component
public interface MessageController {
    List<SendMessage> receive(Long chatId, String text);
}
