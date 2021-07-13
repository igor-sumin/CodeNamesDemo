package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private List<TeamDTO> teams;
}