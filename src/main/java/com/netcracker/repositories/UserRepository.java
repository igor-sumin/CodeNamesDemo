package com.netcracker.repositories;

import com.netcracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // подзапрос -> from u where (select u.room.uniq = :id)
    //           ->
    @Query("SELECT u.user_id, u.captain, u.user_name FROM Users u WHERE Room.uniq_id = :roomId")
    List<User> findAllUsersRoom(String roomId);
}
