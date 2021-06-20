package com.netcracker.services;

import com.netcracker.dto.LoginDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    public String login(LoginDTO loginDTO) {
        return "token";
    }
}