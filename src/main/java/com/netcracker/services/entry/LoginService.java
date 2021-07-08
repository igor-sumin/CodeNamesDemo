package com.netcracker.services.entry;

import com.netcracker.dto.EntryResponseDTO;
import com.netcracker.dto.LoginRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Component
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public EntryResponseDTO login(LoginRequestDTO loginDTO) {
        // TODO: (???) проверка на стороне клиента
        if (this.isEmpty(loginDTO)) {
            System.out.println("empty fields");
        }

        logger.error("alarm");

        // сохранить токен


        return new EntryResponseDTO(
            UUID.nameUUIDFromBytes(
                (
                    loginDTO.getLogin() + loginDTO.getPassword()
                ).getBytes(StandardCharsets.UTF_8)
            ).toString()
        );
    }

    public boolean isEmpty(LoginRequestDTO loginDTO) {
        return StringUtils.isBlank(loginDTO.getLogin()) ||
               StringUtils.isBlank(loginDTO.getPassword());
    }
}