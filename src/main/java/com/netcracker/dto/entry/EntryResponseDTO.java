package com.netcracker.dto.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EntryResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
    private String message;

    public EntryResponseDTO(String token) {
        this.token = token;
    }
}
