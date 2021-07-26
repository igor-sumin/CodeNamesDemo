package com.netcracker.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
public class MessageDTO {
    private String roomRef;
    private String userText;
    private Date createdOn;

    @Override
    public String toString() {
        return "MessageDTO{" +
                "roomRef='" + roomRef + '\'' +
                ", userText='" + userText + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
