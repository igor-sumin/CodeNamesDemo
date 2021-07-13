package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="messages")
public class Messages {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="messages_id")
    private Long id;

    @Column(name="user_text")
    private String userText;

    @Column(name="user_id", insertable = false, updatable = false)
    private int userId;

    @Column(name="wired")
    private int wired;

    @Column(name="created_on")
    private Timestamp createdOn;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
}