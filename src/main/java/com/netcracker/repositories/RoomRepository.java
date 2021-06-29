package com.netcracker.repositories;

import com.netcracker.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByUniqId(String uniqId);
}
