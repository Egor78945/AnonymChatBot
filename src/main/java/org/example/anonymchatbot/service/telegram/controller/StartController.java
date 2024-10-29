package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(title = "Контроллер для команды /start", description = "Класс, обрабатывающий запрос по адресу /start")
@Service
public class StartController implements MessageController{
    @Override
    public List<BotApiMethod<? extends Serializable>> receive(Message message) {
        Long chatId = message.getChatId();
        List<BotApiMethod<? extends Serializable>> response = new ArrayList<>();
        response.add(new SendMessage(chatId.toString(), "Welcome to the chat bot. To start search companions, use the command - /find. To interrupt a dialog with user, use the command - /stop."));
        return response;
    }
}
