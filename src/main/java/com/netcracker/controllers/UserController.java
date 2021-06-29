package com.netcracker.controllers;

import com.netcracker.dto.UserDTO;
import com.netcracker.entities.User;
import com.netcracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: фильтры (?)
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public UserDTO getUser(@RequestParam long id) {
        return userService.getUser(id);
    }


    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
