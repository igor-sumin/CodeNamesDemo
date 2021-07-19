package com.netcracker.services;

import com.netcracker.dto.RoomDTO;
import com.netcracker.entities.Room;
import com.netcracker.entities.Team;
import com.netcracker.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@Component
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomDTO createRoom() {
        String ref = UUID.randomUUID().toString().toUpperCase().substring(0, 8);
        roomRepository.save(new Room(Collections.emptyList(), ref, "First"));

        return new RoomDTO(ref, Collections.emptyList());
    }

    public RoomDTO findRandRoom() {
        Random rand = new Random();

        int qntRooms = roomRepository.countRooms();
        if (qntRooms < 1) {
            return null;
        }

        Room randRoom = roomRepository.findRandRoom(rand.nextInt(qntRooms));

        List<Team> teams = randRoom.getTeams();
        log.info("teams = " + teams);

        return null;
    }

    public RoomDTO findUniqRoom(String ref) {
        Room room = roomRepository.findByRoomRef(ref);
        return null;
    }
}
