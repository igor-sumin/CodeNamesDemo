package com.netcracker.dto;

public class EntryResponseDTO {
    private final String token;

    public EntryResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
