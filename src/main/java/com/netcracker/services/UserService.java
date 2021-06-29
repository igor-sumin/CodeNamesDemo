package com.netcracker.services;

import com.netcracker.dto.UserDTO;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUser(long id) {
        User user = userRepository
                    .findById(id)
                    .orElseThrow(RuntimeException::new);

        return new UserDTO(user.getId(), user.getCaptain(), user.getUserName());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
