package org.example.anonymchatbot.service.telegram.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.service.dialog.DialogService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Schema(title = "Контроллер коммуникации", description = "Класс, занимающийся обменом сообщениями между пользователями")
@Service
@RequiredArgsConstructor
public class CommunicationController implements MessageController {
    private final DialogService dialogService;

    @Override
    public List<SendMessage> receive(Long chatId, String text) {
        List<SendMessage> result = new ArrayList<>();

        if (!dialogService.waitingForDialog(chatId)) {
            if (dialogService.inDialog(chatId)) {
                result.add(new SendMessage(dialogService.getSecondCompanion(chatId).toString(), text));
            } else {
                result.add(new SendMessage(chatId.toString(), "You don't have active dialogs and you aren't in queue of dialogs. To find companion, use the command - /find."));
            }
        } else {
            result.add(new SendMessage(chatId.toString(), "Companion's not found yet, keep waiting. I'll warn you, when companion will has be found."));
        }
        return result;
    }
}
