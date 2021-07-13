package com.netcracker.services.entry;

import com.netcracker.dto.entry.LoginRequestDTO;
import com.netcracker.entities.User;
import com.netcracker.repositories.EntryRepository;
import com.netcracker.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Component
public class LoginService {
    private UserRepository userRepository;
    private EntryRepository entryRepository;

    @Autowired
    public LoginService(UserRepository userRepository, EntryRepository entryRepository) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
    }

    public String login(LoginRequestDTO loginDTO) {
        log.info("dto1 = " + loginDTO.toString());
        // возвращаем сгенерированный на этапе регистрации токен
        User user = userRepository
                    .findByUserLoginAndUserPassword(
                            loginDTO.getLogin(), loginDTO.getPassword()
                    );

        return entryRepository
                    .findByUserId(user.getUserId())
                    .getUserToken();
    }
}