package org.example.anonymchatbot.service.dialog;

import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.model.entity.Dialog;
import org.example.anonymchatbot.repository.DialogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DialogService {
    private final DialogRepository dialogRepository;

    public boolean waitingForDialog(Long companion){
        return dialogRepository.existsDialogByFirstCompanionAndSecondCompanion(companion, null);
    }

    public boolean inDialog(Long companion){
        return dialogRepository.existsDialogBySecondCompanion(companion);
    }

    public void saveToDialogQueue(Dialog dialog){
        dialogRepository.save(dialog);
    }
}
