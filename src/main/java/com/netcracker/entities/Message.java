package com.netcracker.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="messages_id")
    private Long messagesId;

    @Column(name="user_text")
    private String userText;

    @Column(name="wired")
    private int wired;

    @Column(name="created_on")
    private Date createdOn;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name="room_id")
    private Room room;

    protected Message() {
    }

    @Builder
    public Message(User user, Room room, String userText, int wired, Date createdOn) {
        this.user = user;
        this.room = room;
        this.userText = userText;
        this.wired = wired;
        this.createdOn = createdOn;
    }
}