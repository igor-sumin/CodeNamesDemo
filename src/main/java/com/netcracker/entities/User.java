package com.netcracker.entities;

import com.netcracker.dto.entry.RegisterRequestDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private UserToken userToken;

    @OneToMany(mappedBy="user")
    private List<Message> messages;

    @OneToMany(mappedBy="user")
    private List<UserTeamRels> userTeamRelsList;

    protected User() {
    }

    public User(RegisterRequestDTO registerDTO) {
        this.userLogin = registerDTO.getLogin();
        this.userName = registerDTO.getUserName();
        this.userPassword = registerDTO.getUserPassword();
        this.userEmail = registerDTO.getEmail();
    }
}