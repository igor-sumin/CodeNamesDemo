package com.netcracker.services;

import com.netcracker.dto.LoginRequestDTO;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class AuthService {
    // возвращаем новый токен
    public String login(LoginRequestDTO loginDTO) {
        return UUID.nameUUIDFromBytes(
                (loginDTO.getLogin() + loginDTO.getPassword())
                        .getBytes(StandardCharsets.UTF_8)
        ).toString();
    }
}