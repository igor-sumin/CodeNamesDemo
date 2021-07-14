package com.netcracker.services.entry;

import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.RegisterRequestDTO;
import com.netcracker.entities.Entry;
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
        User user = new User(registerDTO);

        // генерируем токен
        String userToken = new EntryResponseDTO(UUID.nameUUIDFromBytes(
                registerDTO.toString().getBytes(StandardCharsets.UTF_8)
            ).toString()
        ).getToken();

        userRepository.save(user);
        entryRepository.save(new Entry(user.getUserId(), userToken));

        return userToken;
    }
}
