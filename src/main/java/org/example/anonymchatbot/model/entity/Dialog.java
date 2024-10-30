package org.example.anonymchatbot.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Schema(title = "Диалог", description = "Класс, представляюий сессию диалога двух человек")
@Data
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
    @Column(name = "date")
    private Timestamp timestamp;

    public Dialog(Long firstCompanion, Long secondCompanion) {
        this.firstCompanion = firstCompanion;
        this.secondCompanion = secondCompanion;
    }

    public Dialog() {
    }
}
