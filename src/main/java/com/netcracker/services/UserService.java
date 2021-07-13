package com.netcracker.services;

import com.netcracker.dto.UserDTO;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUser(long id) {
        return userRepository
                    .findById(id)
                    .map(u -> new UserDTO(u.getUserId(), u.isCaptain(), u.getUserName()))
                    .orElseThrow(RuntimeException::new);
    }

    public List<UserDTO> getAllUsersRoom(int roomId) {
        return userRepository
                    .findAllByRoom(roomId)
                    .stream()
                    .map(u -> new UserDTO(u.getUserId(), u.isCaptain(), u.getUserName()))
                    .collect(Collectors.toList());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
