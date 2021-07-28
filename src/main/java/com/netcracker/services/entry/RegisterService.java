package com.netcracker.services.entry;

import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.RegisterRequestDTO;
import com.netcracker.entities.UserToken;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import com.netcracker.repositories.UserTokenRepository;
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
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    @Autowired
    public RegisterService(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
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
        userTokenRepository.save(new UserToken(user, userToken));

        return userToken;
    }
}
