package com.netcracker.dto;

import java.sql.Timestamp;

public class MessagesDTO {
    private String userText;
    private int wired;
    private Timestamp createdOn;
    private int userId;

    public MessagesDTO(String userText, int wired, Timestamp createdOn, int userId) {
        this.userText = userText;
        this.wired = wired;
        this.createdOn = createdOn;
        this.userId = userId;
    }

    public String getUserText() {
        return userText;
    }

    public int getWired() {
        return wired;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public int getUserId() {
        return userId;
    }
}
