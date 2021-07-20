package com.netcracker.services;

import com.netcracker.dto.RequestContext;
import com.netcracker.dto.RoomDTO;
import com.netcracker.dto.TeamDTO;
import com.netcracker.dto.UserDTO;
import com.netcracker.entities.Room;
import com.netcracker.entities.Team;
import com.netcracker.entities.User;
import com.netcracker.entities.UserTeamRels;
import com.netcracker.repositories.RoomRepository;
import com.netcracker.repositories.TeamRepository;
import com.netcracker.repositories.UserRepository;
import com.netcracker.repositories.UserTeamRelsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@Component
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserTeamRelsRepository userTeamRelsRepository;

    private RoomDTO getRoom(Room room) {
        log.info("1)room = " + room.getRoomId());

        List<TeamDTO> teamDTOList = new ArrayList<>();
        List<Team> teams = room.getTeams();
        if (teams == null) {
            teams = new ArrayList<>();
        }

        for (Team team : teams) {
             List<UserTeamRels> userTeamRelsList = userTeamRelsRepository.findAllByTeam(team);

            log.info("1.1)team = " + team.getTeamId());

            List<UserDTO> userDTOList = new ArrayList<>();
            for (UserTeamRels userTeamRels : userTeamRelsList) {
                User user = userTeamRels.getUser();

                userDTOList.add(new UserDTO(
                                user.getUserTeamRels().isCaptain(),
                                user.getUserName()
                        )
                );

                log.info("1.1.1)user = " + user.getUserId());
            }

            teamDTOList.add(new TeamDTO(
                            team.getTeamName(), userDTOList
                    )
            );
        }

        return new RoomDTO(room.getRoomRef(), teamDTOList);
    }

    @Autowired
    public RoomService(RoomRepository roomRepository,
                       UserTeamRelsRepository userTeamRelsRepository
    ) {
        this.roomRepository = roomRepository;
        this.userTeamRelsRepository = userTeamRelsRepository;
    }

    public RoomDTO createRoom() {
        String roomRef = UUID.randomUUID().toString().toUpperCase().substring(0, 8);
        Room room = new Room(roomRef, "room_" + roomRef.substring(0, 3));

        roomRepository.save(room);
        return this.getRoom(room);
    }

    public RoomDTO findRandRoom() {
        Random rand = new Random();

        int qntRooms = roomRepository.countRooms();
        if (qntRooms < 1) {
            return null;
        }

        return this.getRoom(
                roomRepository.findRandRoom(
                        rand.nextInt(qntRooms)
                )
        );
    }

    public RoomDTO findRoom(String ref) {
        Room room = roomRepository.findByRoomRef(ref);
        if (room == null) {
            return null;
        }

        return this.getRoom(room);
    }
}
