package com.netcracker.services.entry;

import com.netcracker.controllers.CodeNamesExceptions;
import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.LoginRequestDTO;
import com.netcracker.entities.UserToken;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;

import com.netcracker.repositories.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Component
public class LoginService {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    @Autowired
    public LoginService(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }

    public String login(LoginRequestDTO loginDTO) {
        User user = Optional.ofNullable(
                userRepository.findByUserLogin(loginDTO.getUserLogin())
        ).orElseThrow(() -> new CodeNamesExceptions("User not found."));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String existingPassword = loginDTO.getUserPassword();
        String dbPassword = user.getUserPassword();

        if (!passwordEncoder.matches(existingPassword, dbPassword)) {
            throw new CodeNamesExceptions("Wrong password.");
        }

        String userToken = new EntryResponseDTO(
                UUID.nameUUIDFromBytes(loginDTO.toString().getBytes(StandardCharsets.UTF_8)).toString()
        ).getToken();

        // обновляем токен
        UserToken userTokens = user.getUserToken();
        userTokens.setUserToken(userToken);
        userTokenRepository.save(userTokens);

        return userToken;
    }
}