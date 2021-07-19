package com.netcracker.repositories;

import com.netcracker.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<UserToken, Long> {
    UserToken findByUserToken(String token);
}
