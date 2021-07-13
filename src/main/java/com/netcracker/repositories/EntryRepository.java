package com.netcracker.repositories;

import com.netcracker.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    Entry findByUserId(Long userId);
    Entry findByUserToken(String token);
}
