package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class MessagesDTO {
    private Long userId;
    private String userText;
    private int wired;
    private Timestamp createdOn;
}
