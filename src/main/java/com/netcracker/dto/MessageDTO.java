package com.netcracker.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
public class MessageDTO {
    private String userText;
    private Date createdOn;

    @Override
    public String toString() {
        return "MessageDTO{" +
                ", userText='" + userText + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
