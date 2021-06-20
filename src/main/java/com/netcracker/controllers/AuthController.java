package com.netcracker.controllers;

import com.netcracker.dto.LoginDTO;
import com.netcracker.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        // if
        return ResponseEntity.ok(token);
    }

    /*@ControllerAdvice
    public class ResponseExceptionHandler {
        @ExceptionHandler(ResponseStatusException.class)
        public final ResponseEntity<CommonResponse<Void>> handleAllExceptions(ResponseStatusException ex, WebRequest request) {
            log.error("Error was occured: ", ex);

            return new ResponseEntity<String>
    }*/
}