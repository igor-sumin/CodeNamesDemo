package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private boolean isCaptain;
    private String userName;
}
