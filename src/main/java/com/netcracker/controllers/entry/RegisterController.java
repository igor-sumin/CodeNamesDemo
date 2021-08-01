package com.netcracker.controllers.entry;

import com.netcracker.controllers.CodeNamesExceptions;
import com.netcracker.dto.entry.EntryResponseDTO;
import com.netcracker.dto.entry.RegisterRequestDTO;
import com.netcracker.services.entry.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("")
    public ResponseEntity<EntryResponseDTO> registerUser(@RequestBody RegisterRequestDTO registerDTO) {
        String token = registerService.register(registerDTO);
        return ResponseEntity.ok(new EntryResponseDTO(token, "Created account."));
    }
}
