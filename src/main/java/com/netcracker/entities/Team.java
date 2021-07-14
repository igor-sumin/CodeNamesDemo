package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="team_id")
    private Long id;

    @Column(name="team_name")
    private String teamName;

    @Column(name="qnt_points")
    private int qntPoints;
}
