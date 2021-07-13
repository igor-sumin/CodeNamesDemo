package com.netcracker.services.entry;

import com.netcracker.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryService {
    private final EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public boolean authorize(String token) {
         return entryRepository.findByUserToken(token) != null;
    }
}
