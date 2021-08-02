package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomDTO {
    private String roomRef;
    private String roomName;
    private List<TeamDTO> teams;
}