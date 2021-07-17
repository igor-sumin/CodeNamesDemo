package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="room_id")
    private Long id;

    @Column(name="ref")
    private String ref;

    @OneToMany(mappedBy="room")
    private List<Team> teams;

    protected Room() {
    }
}
