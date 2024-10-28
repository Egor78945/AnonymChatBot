package org.example.anonymchatbot.repository;

import org.example.anonymchatbot.model.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    boolean existsDialogByFirstCompanionAndSecondCompanion(Long firstCompanion, Long secondCompanion);

    @Query("SELECT CASE WHEN EXISTS(SELECT id FROM Dialog WHERE secondCompanion=?1) THEN TRUE ELSE FALSE END")
    boolean existsDialogBySecondCompanion(Long secondCompanion);

    @Query("SELECT COUNT(*) FROM Dialog WHERE secondCompanion IS NOT NULL")
    Integer getDialogCountBySecondCompanionIsNotNull();

    @Query("SELECT COUNT(*) FROM Dialog WHERE secondCompanion IS NULL")
    Integer getDialogCountBySecondCompanionIsNull();

    List<Dialog> findAllBySecondCompanionIsNull();

    @Query("SELECT secondCompanion FROM Dialog WHERE firstCompanion=?1")
    Long findDialogSecondCompanionByFirstCompanion(Long firstCompanion);
}
