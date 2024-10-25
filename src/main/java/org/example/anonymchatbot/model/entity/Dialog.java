package org.example.anonymchatbot.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(title = "Диалог", description = "Класс, представляюий сессию диалога двух человек")
@Entity
@Table(name = "conversation")
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_companion")
    private Long firstCompanion;
    @Column(name = "second_companion")
    private Long secondCompanion;

    public Dialog(Long firstCompanion, Long secondCompanion) {
        this.firstCompanion = firstCompanion;
        this.secondCompanion = secondCompanion;
    }

    public Dialog() {
    }
}
