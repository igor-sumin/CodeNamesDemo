package com.netcracker.dto.entry;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {
    private String userLogin;
    private String userPassword;
}
