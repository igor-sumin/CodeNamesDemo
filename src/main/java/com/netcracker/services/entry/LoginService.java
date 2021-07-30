package com.netcracker.services.entry;

import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.LoginRequestDTO;
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
public class LoginService {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    @Autowired
    public LoginService(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }

    public String login(LoginRequestDTO loginDTO) {
        String userToken = new EntryResponseDTO(UUID.nameUUIDFromBytes(
                loginDTO.toString().getBytes(StandardCharsets.UTF_8)
            ).toString()
        ).getToken();

        User user = userRepository.findByUserLoginAndUserPassword(
                            loginDTO.getUserLogin(), loginDTO.getUserPassword()
                    );

        if (user == null) {
            return "";
        }

        // обновляем токен
        UserToken userTokens = user.getUserToken();
        userTokens.setUserToken(userToken);
        userTokenRepository.save(userTokens);

        return userToken;
    }
}