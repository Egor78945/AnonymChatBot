package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.service.dialog.DialogService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(title = "Контроллер коммуникации", description = "Класс, занимающийся обменом сообщениями между пользователями")
@Service
@RequiredArgsConstructor
public class CommunicationController implements MessageController {
    private final DialogService dialogService;

    @Override
    public List<BotApiMethod<? extends Serializable>> receive(Message message) {
        List<BotApiMethod<? extends Serializable>> result = new ArrayList<>();
        Long chatId = message.getChatId();
        if (!dialogService.waitingForDialog(chatId)) {
            if (dialogService.inDialog(chatId)) {
                CopyMessage redirect = new CopyMessage(dialogService.getSecondCompanion(chatId).toString(), chatId.toString() , message.getMessageId());
                result.add(redirect);
            } else {
                result.add(new SendMessage(chatId.toString(), "You don't have active dialogs and you aren't in queue of dialogs. To find companion, use the command - /find."));
            }
        } else {
            result.add(new SendMessage(chatId.toString(), "Companion's not found yet, keep waiting. I'll warn you, when companion will has be found."));
        }
        return result;
    }
}
