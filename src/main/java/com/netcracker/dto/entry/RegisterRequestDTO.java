package com.netcracker.dto;

public class RegisterRequestDTO {
    private final String login;
    private final String name;
    private final String password;
    private final String email;

    public RegisterRequestDTO(String login, String name, String password, String email) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.email = email;
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

    public String getEmail() { return email; }
}
