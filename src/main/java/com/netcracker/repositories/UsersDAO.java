package com.netcracker.repositories;

import com.netcracker.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDAO {
    private static final List<User> users = new ArrayList<>();

    @Value("${spring.datasource.url}")
    private static String URL;
    @Value("${spring.datasource.username}")
    private static String USERNAME;
    @Value("${spring.datasource.password}")
    private static String PASSWORD;

    static {
        users.add(new User("Igor", "123", "Igor@mail.ru"));
        users.add(new User("Dima", "321", "Dima@mail.ru"));
        users.add(new User("Sasha", "455", "Sasha@gmail.com"));
    }

    public List<User> findAll() {
        return users;
    }
}