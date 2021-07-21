package com.netcracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="team_id")
    private Long teamId;

    @Column(name="team_name")
    private String teamName;

    @Column(name="qnt_points")
    private int qntPoints;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name="room_id")
    private Room room;

    @OneToMany(mappedBy="team")
    private List<UserTeamRels> userTeamRelsList;

    protected Team() {
    }

    public Team(Room room, String teamName, int qntPoints) {
        this.room = room;
        this.teamName = teamName;
        this.qntPoints = qntPoints;
    }

    public Team(List<UserTeamRels> userTeamRelsList, String teamName, int qntPoints, Room room) {
        this.userTeamRelsList = userTeamRelsList;
        this.teamName = teamName;
        this.qntPoints = qntPoints;
        this.room = room;
    }
}
