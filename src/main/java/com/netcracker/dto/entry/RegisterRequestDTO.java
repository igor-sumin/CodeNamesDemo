package com.netcracker.dto.entry;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDTO {
    private String login;
    private String userName;
    private String userPassword;
    private String email;
}
