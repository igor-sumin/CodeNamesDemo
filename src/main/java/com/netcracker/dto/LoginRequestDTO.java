package com.netcracker.dto;

public class LoginRequestDTO {
    private String login;
    private String password;

    public LoginRequestDTO() {}

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
