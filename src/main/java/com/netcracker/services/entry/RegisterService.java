package com.netcracker.services.entry;

import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.RegisterRequestDTO;
import com.netcracker.entities.UserToken;
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
public class RegisterService {
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;

    @Autowired
    public RegisterService(EntryRepository entryRepository, UserRepository userRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
    }

    public String register(RegisterRequestDTO registerDTO) {
        if (userRepository.existsByUserName(registerDTO.getUserName())) {
            return "";
        }

        if (userRepository.existsByUserLoginAndUserPassword(registerDTO.getLogin(), registerDTO.getUserPassword())) {
            return "";
        }

        // генерируем токен
        String userToken = new EntryResponseDTO(UUID.nameUUIDFromBytes(
                registerDTO.toString().getBytes(StandardCharsets.UTF_8)
            ).toString()
        ).getToken();

        User user = new User(registerDTO);
        userRepository.save(user);
        entryRepository.save(new UserToken(user, userToken));

        return userToken;
    }
}
