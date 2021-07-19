package com.netcracker.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_team_rels")
public class UserTeamRels {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @Column(name="is_captain")
    private boolean isCaptain;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(targetEntity = Team.class)
    @JoinColumn(name="team_id")
    private Team team;

    protected UserTeamRels() {
    }
}
