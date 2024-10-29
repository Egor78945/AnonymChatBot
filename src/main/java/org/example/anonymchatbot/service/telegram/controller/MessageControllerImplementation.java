package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Schema(title = "Маршрутизатор сообщений", description = "Класс, распределяющий сообщение по подходящим контроллерам")
@Service
@RequiredArgsConstructor
public class MessageControllerImplementation {
    private final Map<String, MessageController> messageController;
    private final FindController findController;
    private final CommunicationController communicationController;
    private final StopController stopController;
    private final StartController startController;

    public List<BotApiMethod<? extends Serializable>> receive(Message message) {
        List<BotApiMethod<? extends Serializable>> result;
        String messageText = message.getText();
        if (messageText == null || !messageText.startsWith("/")) {
            result = communicationController.receive(message);
        } else {
            result = messageController.get(messageText).receive(message);
        }
        return result;
    }

    @PostConstruct
    public void initMessageController() {
        messageController.put("/start", startController);
        messageController.put("/find", findController);
        messageController.put("/stop", stopController);
    }
}
