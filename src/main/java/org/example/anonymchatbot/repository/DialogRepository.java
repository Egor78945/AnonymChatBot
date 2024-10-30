package org.example.anonymchatbot.repository;

import org.example.anonymchatbot.model.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Modifying
    @Query("DELETE FROM Dialog WHERE firstCompanion=?1 OR secondCompanion=?1")
    void removeDialogByCompanion(Long companion);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dialog WHERE firstCompanion IN ?1 OR secondCompanion IN ?1")
    void removeDialogByCompanion(List<Long> companion);

    @Query("SELECT firstCompanion FROM Dialog WHERE ?1 - EXTRACT(HOUR FROM timestamp) >= ?2 AND secondCompanion IS NULL")
    List<Long> findFirstCompanionWithExpiredSessionWithNullSecondCompanion(int hoursCountNow, int limitHour);

    @Query("SELECT firstCompanion FROM Dialog WHERE ?1 - EXTRACT(HOUR FROM timestamp) >= ?2 AND secondCompanion IS NOT NULL")
    List<Long> findFirstCompanionWithExpiredSessionWithNotNullSecondCompanion(int hoursCountNow, int limitHour);
}
