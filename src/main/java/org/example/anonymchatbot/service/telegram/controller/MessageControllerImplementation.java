package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.Map;

@Schema(title = "Маршрутизатор сообщений", description = "Класс, распределяющий сообщение по подходящим контроллерам")
@Service
@RequiredArgsConstructor
public class MessageControllerImplementation {
    private final Map<String, MessageController> messageController;
    private final FindController findController;

    public List<SendMessage> receive(Long chatId, String text) {
        List<SendMessage> result;
        if(text.startsWith("/")) {
            result = messageController.get(text).receive(chatId, text);
        } else {
            result = List.of(new SendMessage(chatId.toString(), "developing..."));
        }
        return result;
    }

    @PostConstruct
    public void initMessageController(){
        messageController.put("/find", findController);
    }
}
