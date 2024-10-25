package org.example.anonymchatbot.service.telegram.controller;

import org.springframework.stereotype.Component;

@Component
public interface MessageController {
    String receive(String chatId) throws Exception;
}
