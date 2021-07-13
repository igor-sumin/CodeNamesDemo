package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entry")
public class Entry {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="entry_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_token")
    private String userToken;

    public Entry(Long userId, String userToken) {
        this.userId = userId;
        this.userToken = userToken;
    }
}
