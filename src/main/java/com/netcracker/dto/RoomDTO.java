package com.netcracker.dto;

import java.util.List;

public class RoomDTO {
    private Long id;
    private List<TeamDTO> teams;

    public RoomDTO(Long id, List<TeamDTO> teams) {
        this.id = id;
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public List<TeamDTO> getTeams() {
        return teams;
    }
}