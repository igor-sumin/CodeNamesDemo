package com.netcracker.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamDTO {
    private String teamName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UserDTO> users;
}
