package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.service.dialog.DialogService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(title = "Контроллер для команды /stop", description = "Класс, обрабатывающий запрос по адресу /stop")
@Service
@RequiredArgsConstructor
public class StopController implements MessageController {
    private final DialogService dialogService;

    @Override
    public List<BotApiMethod<? extends Serializable>> receive(Message message) {
        List<BotApiMethod<? extends Serializable>> result = new ArrayList<>();
        Long chatId = message.getChatId();

        if (dialogService.waitingForDialog(chatId)) {
            dialogService.abortDialog(chatId);
            result.add(new SendMessage(chatId.toString(), "You've cancelled a search of companion."));
        } else if (dialogService.inDialog(chatId)) {
            Long companion = dialogService.getSecondCompanion(chatId);
            dialogService.abortDialog(chatId);
            result.add(new SendMessage(chatId.toString(), "You've aborted the dialog with this user."));
            result.add(new SendMessage(companion.toString(), "Your companion's aborted the dialog with you."));
        } else {
            result.add(new SendMessage(chatId.toString(), "You're don't have active dialogs and you're not in queue of dialogs."));
        }

        return result;
    }
}
