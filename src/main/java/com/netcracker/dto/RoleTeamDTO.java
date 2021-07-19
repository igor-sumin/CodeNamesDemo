package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleTeamDTO {
    private boolean isCaptain;
    private String teamName;
}