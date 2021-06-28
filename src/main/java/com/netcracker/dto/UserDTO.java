package com.netcracker.dto;

public class UserDTO {
    private long USER_ID;
    private boolean captain;
    private String name;

    public UserDTO(long userid, boolean captain, String name) {
        this.USER_ID = userid;
        this.captain = captain;
        this.name = name;
    }

    public boolean getCaptain() {
        return captain;
    }

    public String getName() {
        return name;
    }
}
