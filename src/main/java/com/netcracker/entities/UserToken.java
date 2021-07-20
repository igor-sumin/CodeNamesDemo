package com.netcracker.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_tokens")
public class UserToken {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_token")
    private String userToken;

    @MapsId
    @OneToOne(
            fetch = FetchType.EAGER,
            targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    protected UserToken() {
    }

    public UserToken(User user, String userToken) {
        this.user = user;
        this.userToken = userToken;
    }

    public UserToken(String userToken) {
        this.userToken = userToken;
    }
}
