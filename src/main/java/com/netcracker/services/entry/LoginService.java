package com.netcracker.services.entry;

import com.netcracker.dto.EntryResponseDTO;
import com.netcracker.dto.LoginRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Component
public class LoginService {
    // возвращаем новый токен
    public EntryResponseDTO login(LoginRequestDTO loginDTO) {
        return new EntryResponseDTO(
            UUID.nameUUIDFromBytes(
                (
                    loginDTO.getLogin() + loginDTO.getPassword()
                ).getBytes(StandardCharsets.UTF_8)
            ).toString()
        );
    }

    public boolean isEmpty(LoginRequestDTO loginDTO) {
        return loginDTO.getLogin().isEmpty() || loginDTO.getPassword().isEmpty();
    }
}