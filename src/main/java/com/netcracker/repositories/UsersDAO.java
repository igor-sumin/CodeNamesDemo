package com.netcracker.repositories;

import com.netcracker.entities.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDAO {
    private static final List<User> users = new ArrayList<>();
    private static final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    static {
        users.add(new User(0L,"Igor", "123", true, 1, timestamp, 4));
        users.add(new User(1L, "Dima", "321", false, 1, timestamp, 4));
        users.add(new User(2L, "Sasha", "455", false, 0, timestamp, 4));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(long id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
}