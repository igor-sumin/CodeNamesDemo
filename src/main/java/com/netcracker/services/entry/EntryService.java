package com.netcracker.services.entry;

import com.netcracker.entities.UserToken;
import com.netcracker.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryService {
    private final UserTokenRepository userTokenRepository;

    @Autowired
    public EntryService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public UserToken authorize(String token) {
        return userTokenRepository.findByUserToken(token);
    }
}
