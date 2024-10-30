package org.example.anonymchatbot.service.dialog.observer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.service.dialog.DialogService;
import org.example.anonymchatbot.service.telegram.bot.AnonymChatBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Schema(title = "Наблюдатель за активными диалогами", description = "Класс, представляющий наблюдателя, который следит за длительностью активных диалогов")
@Service
@RequiredArgsConstructor
public class DialogSessionExpiredObserver implements DialogObserver{
    private final DialogService dialogService;
    private final AnonymChatBot anonymChatBot;

    @Override
    public void observe() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10_800_000);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date(System.currentTimeMillis() + 10_800_000));
                    int hoursNow = calendar.get(Calendar.HOUR_OF_DAY);

                    List<Long> companions = dialogService.getExpiredDialogsBySecondCompanionIsNotNull(hoursNow, 3);

                    dialogService.abortDialog(companions);

                    for (Long companion : companions) {
                        anonymChatBot.sendMessage(new SendMessage(companion.toString(), "Sorry, but the dialog lasted more than 3 hours, so it had to be interrupted."));
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
