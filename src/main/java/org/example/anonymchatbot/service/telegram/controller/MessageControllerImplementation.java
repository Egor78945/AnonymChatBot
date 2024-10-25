package org.example.anonymchatbot.service.telegram.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageControllerImplementation {
    private final Map<String, MessageController> messageController;
    private final FindController findController;

    public List<SendMessage> receive(Long chatId, String text) throws Exception {
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
