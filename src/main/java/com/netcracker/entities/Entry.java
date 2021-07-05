package com.netcracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entry")
public class Entry {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "token")
    private String token;

    protected Entry() {}

    public Entry(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
