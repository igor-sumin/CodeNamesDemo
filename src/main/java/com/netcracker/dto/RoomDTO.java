package com.netcracker.dto;

import com.netcracker.entities.Team;

import java.util.List;

public class RoomDTO {
    private int id;
    private List<Team> teams;

    public int getId() {
        return id;
    }

    public List<Team> getTeams() {
        return teams;
    }
}