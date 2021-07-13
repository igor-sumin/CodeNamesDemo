package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class MessagesDTO {
    private String userText;
    private int wired;
    private Timestamp createdOn;
    private int userId;
}
