package com.netcracker.repositories;

import com.netcracker.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface EntryRepository extends JpaRepository<Entry, Long> {
    Entry findByUserId(Long userId);
    Entry findByUserToken(String token);

    @Query("update Entry e set e.userToken = ?2 where e.userId = ?1")
    @Modifying
    @Transactional
    void setEntryByUserId(long userId, String userToken);
}
