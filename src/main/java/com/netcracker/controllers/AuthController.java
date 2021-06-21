package com.netcracker.controllers;

import com.netcracker.dto.LoginRequestDTO;
import com.netcracker.entities.User;
import com.netcracker.repositories.UsersDAO;
import com.netcracker.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UsersDAO users;

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginDTO) {
        String token = authService.login(loginDTO);

        // TODO: realize ResponseExceptionHandler

        if (loginDTO.isEmpty()) {
            return new ResponseEntity<>(
                    "error: login or password is empty",
                    HttpStatus.BAD_REQUEST
            );
        }

        return ResponseEntity.ok(token);
    }



    @GetMapping("/list/{room}/users")
    public List<User> getAllUsers(@PathVariable int room) {
        return users.findAll();
    }
}