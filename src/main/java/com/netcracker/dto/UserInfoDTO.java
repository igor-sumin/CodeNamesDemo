package com.netcracker.dto;

import lombok.*;

@Builder
@Getter
@Setter
public class UserInfoDTO {
    private String roomRef;
    private String teamName;
    private boolean isCaptain;
}