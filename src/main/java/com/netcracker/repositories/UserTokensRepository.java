package com.netcracker.repositories;

import com.netcracker.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTokensRepository extends JpaRepository<UserToken, Long> {
    UserToken findByUserToken(String userToken);
}