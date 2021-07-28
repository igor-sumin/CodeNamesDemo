package com.netcracker.dto.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MessageResponseDTO {
    private MessageDTO info;
    private String userName;
    private boolean isCaptain;
    private String teamName;
}
