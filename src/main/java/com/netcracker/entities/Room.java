package com.netcracker.entities;

import javax.persistence.*;

// TODO: wired with users

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="room_id")
    private Long id;
    @Column(name="uniq_ref")
    private String uniqId;
    @Column(name="amount")
    private int amount;

    protected Room() {
    }

    public Room(long id, String uniqId, int amount) {
        this.id = id;
        this.uniqId = uniqId;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getUniqId() {
        return uniqId;
    }

    public int getAmount() {
        return amount;
    }

    /*@OneToMany(mappedBy = "room")
    private List<User> users;*/
}
