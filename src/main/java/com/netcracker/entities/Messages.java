package com.netcracker.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="messages")
public class Messages {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="messages_id")
    private Long id;
    @Column(name="user_text")
    private String userText;
    @Column(name="user_id")
    private int userId;
    @Column(name="wired")
    private int wired;
    @Column(name="created_on")
    private Timestamp createdOn;

    protected Messages() {
    }

    public Messages(Long id, String userText, int userId, int wired, Timestamp createdOn) {
        this.id = id;
        this.userText = userText;
        this.userId = userId;
        this.wired = wired;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public String getUserText() {
        return userText;
    }

    public int getUserId() {
        return userId;
    }

    public int getWired() {
        return wired;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }
}