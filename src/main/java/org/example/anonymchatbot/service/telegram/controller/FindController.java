package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.model.entity.Dialog;
import org.example.anonymchatbot.service.dialog.DialogService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(title = "Контроллер для команды /find", description = "Класс, обрабатывающий запрос по адресу /find")
@Service
@RequiredArgsConstructor
public class FindController implements MessageController {
    private final DialogService dialogService;

    @Override
    public List<BotApiMethod<? extends Serializable>> receive(Message message) {
        List<BotApiMethod<? extends Serializable>> result = new ArrayList<>();
        Long chatId = message.getChatId();
        if(!dialogService.waitingForDialog(chatId)){
            if(!dialogService.inDialog(chatId)){
                Dialog dialog = new Dialog(chatId, null);
                dialogService.saveDialog(dialog);
                result.add(new SendMessage(chatId.toString(), "You've been added to queue of dialogs, just wait a few seconds..."));
            } else {
                result.add(new SendMessage(chatId.toString(), "You're already in dialog with some user."));
            }
        } else {
            result.add(new SendMessage(chatId.toString(), "You're already in the queue of dialogs, just wait a few seconds..."));
        }
        return result;
    }
}
