package com.netcracker.services;

import com.netcracker.dto.RoomDTO;
import com.netcracker.entities.Room;
import com.netcracker.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomDTO getRoom(String uniqId) {
        Room room = roomRepository.findByUniqId(uniqId);

        // return new RoomDTO(room.getId(), room.getTeams());
        return null;
    }
}
