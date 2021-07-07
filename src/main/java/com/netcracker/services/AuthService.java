package com.netcracker.services;

import com.netcracker.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    @Autowired
    private EntryRepository entryRepository;

    public boolean authorize(String token) {
        // ищем юзера по токену -> нашли -> пропускаем
        // return entryRepository.findByToken(token) != null;
        return true;
    }
}
