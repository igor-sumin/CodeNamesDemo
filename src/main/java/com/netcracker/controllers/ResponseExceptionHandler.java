package com.netcracker.controllers;

import com.netcracker.dto.LoginRequestDTO;
import com.netcracker.services.entry.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public final void handleAllExceptions(ResponseStatusException ex, WebRequest request) {
    }
}
