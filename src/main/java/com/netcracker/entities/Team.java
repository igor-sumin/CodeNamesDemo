package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="team_id")
    private Long teamId;

    @Column(name="room_id")
    private Long roomId;

    @Column(name="team_name")
    private String teamName;

    @Column(name="qnt_points")
    private int qntPoints;

    @ManyToOne
    @JoinColumn(name="room_id", nullable = false)
    private Room room;

    @OneToOne(mappedBy="team")
    private UserTeam userTeam;

    protected Team() {
    }
}
