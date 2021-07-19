package com.netcracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="messages_id")
    private Long messagesId;

    @Column(name="user_text")
    private String userText;

    @Column(name="wired")
    private int wired;

    @Column(name="created_on")
    private Timestamp createdOn;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name="room_id")
    private Room room;

    protected Message() {
    }
}