package com.netcracker.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_team")
public class UserTeam {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="team_id")
    private Long teamId;

    @Column(name="is_captain")
    private boolean isCaptain;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name="team_id", nullable = false)
    private Team team;

    protected UserTeam() {
    }
}
