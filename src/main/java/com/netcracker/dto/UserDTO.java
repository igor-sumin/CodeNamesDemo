package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private long userId;
    private boolean captain;
    private String name;
}
