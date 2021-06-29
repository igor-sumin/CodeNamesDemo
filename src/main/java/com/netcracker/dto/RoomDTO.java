package com.netcracker.dto;

import com.netcracker.entities.Team;

import java.util.List;

public class RoomDTO {
    private Long id;
    private List<Team> teams;

    public RoomDTO(Long id, List<Team> teams) {
        this.id = id;
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public List<Team> getTeams() {
        return teams;
    }
}