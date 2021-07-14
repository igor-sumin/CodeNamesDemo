package com.netcracker.services.entry;

import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.LoginRequestDTO;
import com.netcracker.entities.User;
import com.netcracker.repositories.EntryRepository;
import com.netcracker.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Service
@Component
public class LoginService {
    private final UserRepository userRepository;
    private final EntryRepository entryRepository;

    @Autowired
    public LoginService(UserRepository userRepository, EntryRepository entryRepository) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
    }

    public String login(LoginRequestDTO loginDTO) {
        // генерируем токен
        String userToken = new EntryResponseDTO(UUID.nameUUIDFromBytes(
                loginDTO.toString().getBytes(StandardCharsets.UTF_8)
            ).toString()
        ).getToken();

        User user = userRepository
                    .findUserByUserLoginAndUserPassword(
                            loginDTO.getUserLogin(), loginDTO.getUserPassword()
                    );

        if (user == null) {
            return "";
        }

        // обновляем токен
        entryRepository.setEntryByUserId(user.getUserId(), userToken);

        return userToken;
    }
}