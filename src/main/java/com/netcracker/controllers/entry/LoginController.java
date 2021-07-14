package com.netcracker.controllers.entry;

import com.netcracker.controllers.ResponseExceptionHandler;
import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.LoginRequestDTO;
import com.netcracker.services.entry.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: realize ResponseExceptionHandler

@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("")
    public ResponseEntity<EntryResponseDTO> loginUser(@RequestBody LoginRequestDTO loginDTO) throws ResponseExceptionHandler {
        String token = loginService.login(loginDTO);

        if ( token.isEmpty()) {
            throw new ResponseExceptionHandler();
        }

        return ResponseEntity.ok(new EntryResponseDTO(token));
    }
}