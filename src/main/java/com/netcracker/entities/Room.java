package com.netcracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_ref")
    private String roomRef;

    @Column(name = "room_name")
    private String roomName;

    @OneToMany(mappedBy = "room")
    private List<Team> teams;

    @OneToMany(mappedBy = "room")
    private List<Message> messages;

    protected Room() {
    }

    public Room(String roomRef, String roomName) {
        this.roomRef = roomRef;
        this.roomName = roomName;
    }

    public Room(List<Team> teams, String roomRef, String roomName) {
        this.teams = new ArrayList<>(teams);
        this.roomRef = roomRef;
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomRef='" + roomRef + '\'' +
                ", roomName='" + roomName + '\'' +
                ", teams=" + teams +
                ", messages=" + messages +
                '}';
    }
}
