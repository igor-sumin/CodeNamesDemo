package com.netcracker.dto;


import java.util.List;

public class TeamDTO {
    private String teamName;
    private List<UserDTO> users;

    public TeamDTO(String teamName, List<UserDTO> users) {
        this.teamName = teamName;
        this.users = users;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<UserDTO> getUsers() {
        return users;
    }
}
