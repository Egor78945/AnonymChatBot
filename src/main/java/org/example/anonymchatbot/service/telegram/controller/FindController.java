package org.example.anonymchatbot.service.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.service.redis.RedisService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindController implements MessageController {
    private final RedisService redisService;

    @Override
    public String receive(String chatId) {
        return null;
    }
}
