
package com.netcracker.controllers;

import com.netcracker.dto.UserDTO;
import com.netcracker.entities.Entry;
import com.netcracker.entities.User;
import com.netcracker.repositories.EntryRepository;
import com.netcracker.services.UserService;
import jdk.internal.net.http.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final EntryRepository entryRepository;

    @Autowired
    public UserController(UserService userService, EntryRepository entryRepository) {
        this.userService = userService;
        this.entryRepository = entryRepository;
    }

    @GetMapping("")
    public Pair<UserDTO, Entry> getUser(@RequestParam long id) {
        return Pair.pair(userService.getUser(id), entryRepository.findByUserId(id));
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/list/room")
    public List<UserDTO> getAllUsersRoom(@RequestParam int id) {
        return userService.getAllUsersRoom(id);
    }
}