package com.netcracker.entities;

import com.netcracker.dto.entry.RegisterRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// TODO: updatable ??

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="users_id")
    private long userId;

    @Column(name="user_login")
    private String userLogin;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="captain", insertable = false)
    private boolean captain;

    @Column(name="team", insertable = false, updatable = false)
    private int team;

    @Column(name="room", insertable = false, updatable = false)
    private int room;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="team")
    private Team teamUser;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="room")
    private Room roomUser;

    @OneToMany(mappedBy="user")
    private List<Messages> messages;

    public User(RegisterRequestDTO registerDTO) {
        this.userLogin = registerDTO.getLogin();
        this.userName = registerDTO.getUserName();
        this.userPassword = registerDTO.getUserPassword();
        this.userEmail = registerDTO.getEmail();
    }
}