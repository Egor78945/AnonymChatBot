package org.example.anonymchatbot.service.dialog.observer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.model.entity.Dialog;
import org.example.anonymchatbot.service.dialog.DialogService;
import org.example.anonymchatbot.service.telegram.bot.AnonymChatBot;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.List;

@Schema(title = "Наблюдатель за диалогами", description = "Класс, наблидающий за таблицей диалогов и предпринимающий определённые действия в зависимости от её состояния.")
@Service
@RequiredArgsConstructor
public class DialogQueueObserver implements DialogObserver {
    private final DialogService dialogService;
    private final AnonymChatBot anonymChatBot;

    @Operation(summary = "Связывание собеседников", description = "Метод, создающий поток, который периодически проверяет таблицу на наличие пользователей, желающий начать диалог и связывающий каждую пару пользователей.")
    @EventListener(ContextRefreshedEvent.class)
    public void observe() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                    if (dialogService.getDialogQueueCount() > 1) {
                        List<Dialog> dialogQueue = dialogService.getDialogQueue();
                        int l = 0;
                        int r = dialogQueue.size() - 1;
                        while (l < r) {
                            Dialog ld = dialogQueue.get(l);
                            Dialog rd = dialogQueue.get(r);
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 10_800_000);

                            ld.setTimestamp(timestamp);
                            rd.setTimestamp(timestamp);

                            ld.setSecondCompanion(rd.getFirstCompanion());
                            rd.setSecondCompanion(ld.getFirstCompanion());

                            dialogService.saveDialog(ld);
                            dialogService.saveDialog(rd);

                            String message = "Companion has been found, have a good communication.";

                            anonymChatBot.sendMessage(new SendMessage(ld.getFirstCompanion().toString(), message));
                            anonymChatBot.sendMessage(new SendMessage(rd.getFirstCompanion().toString(), message));
                            l++;
                            r--;
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
