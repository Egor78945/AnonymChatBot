package org.example.anonymchatbot.service.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.model.entity.Dialog;
import org.example.anonymchatbot.service.dialog.DialogService;
import org.example.anonymchatbot.service.telegram.bot.AnonymChatBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindController implements MessageController {
    private final DialogService dialogService;

    @Override
    public List<SendMessage> receive(Long chatId, String text) {
        List<SendMessage> result = new ArrayList<>();
        if(!dialogService.waitingForDialog(chatId)){
            if(!dialogService.inDialog(chatId)){
                Dialog dialog = new Dialog(chatId, null);
                dialogService.saveToDialogQueue(dialog);
                result.add(new SendMessage(chatId.toString(), "You've been added to queue of dialog, just wait a few seconds..."));
            } else {
                result.add(new SendMessage(chatId.toString(), "You already in dialog with some user."));
            }
        } else {
            result.add(new SendMessage(chatId.toString(), "You already in the queue of dialog, just wait a few seconds..."));
        }
        return result;
    }
}
