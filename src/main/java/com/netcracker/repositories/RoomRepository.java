package com.netcracker.repositories;

import com.netcracker.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByUniqId(String uniqId);
}
