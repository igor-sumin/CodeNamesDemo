package com.netcracker.services.entry;

import com.netcracker.dto.EntryResponseDTO;
import com.netcracker.dto.LoginRequestDTO;
import com.netcracker.dto.RegisterRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Component
public class RegisterService {
    // возвращаем новый токен
    public EntryResponseDTO register(RegisterRequestDTO registerDTO) {
        return new EntryResponseDTO(
            UUID.nameUUIDFromBytes(
                (
                    registerDTO.getLogin() + registerDTO.getPassword() +
                    registerDTO.getName() + registerDTO.getCreatedOn()
                ).getBytes(StandardCharsets.UTF_8)
            ).toString()
        );
    }

    public boolean isEmpty(RegisterRequestDTO loginDTO) {
        return loginDTO.getLogin().isEmpty() ||
               loginDTO.getPassword().isEmpty() ||
               loginDTO.getName().isEmpty();
    }
}
