package com.netcracker.dto;

import java.sql.Timestamp;

public class RegisterRequestDTO {
    private final String login;
    private final String name;
    private final String password;
    private final Timestamp createdOn;

    public RegisterRequestDTO(String login, String name, String password, Timestamp createdOn) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.createdOn = createdOn;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }
}
