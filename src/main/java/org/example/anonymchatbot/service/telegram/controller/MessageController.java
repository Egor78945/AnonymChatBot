package org.example.anonymchatbot.service.telegram.controller;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
public interface MessageController {
    List<SendMessage> receive(Long chatId, String text);
}
