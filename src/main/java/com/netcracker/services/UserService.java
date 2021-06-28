package com.netcracker.services;

import com.netcracker.dto.UserDTO;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Получить данные о пользователе
    public UserDTO getUser(long id) {
        User user = userRepository.findById(id).orElseThrow(
                RuntimeException::new
        );

        return new UserDTO(user.getId(), user.getCaptain(), user.getUserName());
    }

    // Получить всех участников комнаты
    public List<UserDTO> findAll(String uniqId) {
        return userRepository.findAllUsersRoom(uniqId);
    }
}
