package com.netcracker.entities;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    private String userPassword;
    private String email;
    private boolean captain;
    @ManyToOne
    @JoinColumn(name="room_id", referencedColumnName="room", nullable=false)
    private Room room;

    // wired private Room

    protected User() {}

    public User(String userName, String userPassword, String email, boolean captain) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.captain = captain;
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

    public String getEmail() {
        return email;
    }

    public boolean getCaptain() {
        return captain;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", firstName=" + userName + ", lastName=" + userPassword + ", email=" + email + "]";
    }
}