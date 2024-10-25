package org.example.anonymchatbot.model.entity;

import jakarta.persistence.*;

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
}
