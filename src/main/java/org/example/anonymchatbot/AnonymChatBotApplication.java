package org.example.anonymchatbot;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Schema(title = "AnonymChatBotApplication", description = "Приложение представляет собой backend для Телеграмм-бота, который выполняет функцию анонимного чата")
@SpringBootApplication
public class AnonymChatBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnonymChatBotApplication.class, args);
    }

}
