package com.netcracker.repositories;

import com.netcracker.entities.Message;
import com.netcracker.entities.Room;
import com.netcracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUser(User user);
    List<Message> findAllByRoom(Room room);
    List<Message> findAllByRoomAndUser(Room room, User user);

//    @Query(
//            value = "delete from message m where m.id in ?1",
//            nativeQuery = true)
//    void deleteAllUserMessages(List<Message> messageIds);

//    @Query(
//            value = "delete from message m where m.user_id = :userId",
//            nativeQuery = true)
//    @Modifying
//    void deleteAllUserMessages(Long userId);
}
