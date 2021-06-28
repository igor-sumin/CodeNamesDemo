package com.netcracker.controllers;

import com.netcracker.dto.LoginResponseDTO;
import com.netcracker.dto.UserDTO;
import com.netcracker.entities.User;
import com.netcracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // фильтры

    @GetMapping("/login")
    public UserDTO getUser(@RequestParam long id) {
        return userService.getUser(id);
    }

    @GetMapping("/list/{room}/users")
    public List<UserDTO> getAllUsers(@PathVariable String uniqId) {
        return userService.findAll(uniqId);
    }
}
