package org.example.anonymchatbot.service.dialog.observer;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "Наблюдатель за диалогами", description = "Интерфейс, представляющий наблюдателя за таблицей диалогов")
public interface DialogObserver {
    void observe();
}
