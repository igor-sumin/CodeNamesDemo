package com.netcracker.dto;

public class LoginRequestDTO {
    private final String login;
    private final String password;

    public LoginRequestDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
