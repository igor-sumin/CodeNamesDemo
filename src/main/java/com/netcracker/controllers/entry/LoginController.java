package com.netcracker.controllers.entry;

import com.netcracker.dto.EntryResponseDTO;
import com.netcracker.dto.LoginRequestDTO;
import com.netcracker.services.entry.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        // System.out.println("loginDTO = " + loginDTO.getLogin() + ", " + loginDTO.getPassword());
        String token = loginService.login(loginDTO).getToken();
        return ResponseEntity.ok(new EntryResponseDTO(token));
    }
}