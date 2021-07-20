package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleTeamDTO {
    private String roomRef;
    private boolean isCaptain;
    private String teamName;
}