package com.netcracker.repositories;

import com.netcracker.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
    Entry findByUserId(int userId);
    Entry findByToken(String token);
}
