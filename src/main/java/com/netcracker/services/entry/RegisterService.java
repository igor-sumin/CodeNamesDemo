package com.netcracker.services.entry;

import com.netcracker.controllers.CodeNamesExceptions;
import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.RegisterRequestDTO;
import com.netcracker.entities.UserToken;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import com.netcracker.repositories.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Service
@Component
public class RegisterService {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    private static boolean validEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    @Autowired
    public RegisterService(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }

    public String register(RegisterRequestDTO registerDTO) {
        if (!validEmail(registerDTO.getEmail())) {
            throw new CodeNamesExceptions("Invalid email.");
        }

        if (userRepository.existsByUserLoginOrUserName(registerDTO.getLogin(), registerDTO.getUserName())) {
            throw new CodeNamesExceptions("User already exist.");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(registerDTO.getUserPassword());

        // генерируем токен
        String userToken = new EntryResponseDTO(UUID.nameUUIDFromBytes(
                registerDTO.toString().getBytes(StandardCharsets.UTF_8)
            ).toString()
        ).getToken();

        User user = new User(registerDTO, hashedPassword);
        userRepository.save(user);
        userTokenRepository.save(new UserToken(user, userToken));

        return userToken;
    }
}
