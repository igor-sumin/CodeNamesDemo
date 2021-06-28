package com.netcracker.repositories;

import com.netcracker.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room getByUniqRef(String uniqRef);
}
