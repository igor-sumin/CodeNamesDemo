package com.netcracker.entities;

import javax.persistence.*;
import java.sql.Timestamp;

// TODO: связывание между entities

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="users_id")
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="captain")
    private boolean captain;

    @Column(name="team")
    private int team;

    @Column(name="created_on")
    private Timestamp createdOn;

    @Column(name="room")
    private int room;

    /*@ManyToOne
    @JoinColumn(name="room_id", referencedColumnName="roomUser", nullable=false)
    private Room roomUser;*/

    protected User() {}

    public User(String userName, String userPassword, boolean captain, int team, Timestamp createdOn, int room) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.captain = captain;
        this.team = team;
        this.createdOn = createdOn;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean getCaptain() {
        return captain;
    }

    public int getTeam() {
        return team;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public int getRoom() {
        return room;
    }
}