package com.netcracker.repositories;

import com.netcracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserLogin(String userLogin);
    User findByUserName(String userName);
    Boolean existsByUserLoginOrUserName(String userLogin, String userName);
}