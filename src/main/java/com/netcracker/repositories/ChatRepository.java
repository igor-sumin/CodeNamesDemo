package com.netcracker.repositories;

import com.netcracker.entities.Message;
import com.netcracker.entities.Room;
import com.netcracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRoom(Room room);
    List<Message> findAllByRoomAndUser(Room room, User user);
}
