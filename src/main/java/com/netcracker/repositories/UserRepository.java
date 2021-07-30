package com.netcracker.repositories;

import com.netcracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserLoginAndUserPassword(String userLogin, String userPassword);
    User findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByUserLoginAndUserPassword(String userLogin, String userPassword);

}