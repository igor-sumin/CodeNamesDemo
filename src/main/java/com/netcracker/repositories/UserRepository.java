package com.netcracker.repositories;

import com.netcracker.entities.User;
import com.netcracker.entities.UserTeamRels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserLoginAndUserPassword(String userLogin, String userPassword);

    List<User> findAllByUserTeamRels(UserTeamRels userTeamRels);
}