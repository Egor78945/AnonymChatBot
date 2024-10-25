package org.example.anonymchatbot.service.dialog;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.model.entity.Dialog;
import org.example.anonymchatbot.repository.DialogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DialogService {
    private final DialogRepository dialogRepository;

    @Operation(summary = "Проверка ожидания пользователя", description = "Метод, проверяющий, ждёт ли конкретный пользователь собеседника для диалога")
    public boolean waitingForDialog(Long companion){
        return dialogRepository.existsDialogByFirstCompanionAndSecondCompanion(companion, null);
    }

    @Operation(summary = "Проверка занятости пользователя", description = "Метод, проверяющий, находиться ли пользователь в диалоге с кем-то")
    public boolean inDialog(Long companion){
        return dialogRepository.existsDialogBySecondCompanion(companion);
    }

    @Operation(summary = "Сохранение диалога", description = "Метод, сохраняющий новосозданный диалог")
    public void saveToDialogQueue(Dialog dialog){
        dialogRepository.save(dialog);
    }
}
