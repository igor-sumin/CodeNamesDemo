package com.netcracker.repositories;

import com.netcracker.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDAO {
    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User("Igor", "123"));
        users.add(new User("Dima", "321"));
        users.add(new User("Sasha", "455"));
    }

    public List<User> findAll() {
        return users;
    }
}