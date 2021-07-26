package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomDTO {
    private String roomRef;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TeamDTO> teams;
}