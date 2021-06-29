package com.netcracker.controllers.entry;

import com.netcracker.dto.EntryResponseDTO;
import com.netcracker.dto.LoginRequestDTO;
import com.netcracker.dto.RegisterRequestDTO;
import com.netcracker.services.entry.LoginService;
import com.netcracker.services.entry.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("register")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("")
    public ResponseEntity<String> loginUser(@RequestBody RegisterRequestDTO registerDTO) {
         String token = registerService.register(registerDTO).getToken();

        if (registerService.isEmpty(registerDTO)) {
            return new ResponseEntity<>(
                    "error: login or password is empty",
                    HttpStatus.BAD_REQUEST
            );
        }

        return ResponseEntity.ok(token);
    }
}
