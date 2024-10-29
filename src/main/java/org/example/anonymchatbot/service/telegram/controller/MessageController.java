package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.List;

@Schema(title = "Контроллер сообщений", description = "Интерфейс представляет контроллер сообщений")
public interface MessageController {
    List<BotApiMethod<? extends Serializable>> receive(Message message);
}
