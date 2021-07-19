package com.netcracker.repositories;

import com.netcracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserLoginAndUserPassword(String userLogin, String userPassword);
}