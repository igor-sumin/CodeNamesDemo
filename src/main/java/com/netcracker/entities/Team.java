package com.netcracker.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String teamName;
    private int qntPoints;
    private Timestamp createdOn;

    protected Team() {}

    public Team(String teamName, int qntPoints, Timestamp createdOn) {
        this.teamName = teamName;
        this.qntPoints = qntPoints;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getQntPoints() {
        return qntPoints;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", teamName=" + teamName + ", qntPoints=" + qntPoints + ", createdOn=" + createdOn + "]";
    }
}
