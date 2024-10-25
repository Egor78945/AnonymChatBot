package org.example.anonymchatbot.repository;

import org.example.anonymchatbot.model.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    boolean existsDialogByFirstCompanionAndSecondCompanion(Long firstCompanion, Long secondCompanion);
    boolean existsDialogBySecondCompanion(Long secondCompanion);
}
