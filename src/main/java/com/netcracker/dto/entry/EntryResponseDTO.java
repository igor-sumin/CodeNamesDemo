package com.netcracker.dto.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryResponseDTO {
    private String token;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public EntryResponseDTO(String token) {
        this.token = token;
    }
}
