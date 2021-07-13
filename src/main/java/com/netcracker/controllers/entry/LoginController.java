package com.netcracker.controllers.entry;

import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.LoginRequestDTO;
import com.netcracker.services.entry.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: realize ResponseExceptionHandler

@RestController
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("")
    public ResponseEntity<EntryResponseDTO> loginUser(@RequestBody LoginRequestDTO loginDTO) {
        String token = loginService.login(loginDTO);
        return ResponseEntity.ok(new EntryResponseDTO(token));
    }
}