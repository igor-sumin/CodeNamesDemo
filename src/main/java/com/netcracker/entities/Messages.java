package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name="messages")
public class Messages {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="messages_id")
    private Long messagesId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="user_text")
    private String userText;

    @Column(name="wired")
    private int wired;

    @Column(name="created_on")
    private Timestamp createdOn;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    protected Messages() {
    }
}