package com.netcracker.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Room {
    @Id
    private long id;
    private String uniqRef;

    /*@OneToMany(mappedBy = "room")
    private List<User> users;*/
}
