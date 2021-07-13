package com.netcracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamDTO {
    private String teamName;
    private List<UserDTO> users;
}
