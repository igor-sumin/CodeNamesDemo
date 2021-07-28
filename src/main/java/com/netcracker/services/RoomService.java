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
import com.netcracker.repositories.UserRepository;
import com.netcracker.repositories.UserTeamRelsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Component
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserTeamRelsRepository userTeamRelsRepository;
    private final UserRepository userRepository;

    private RoomDTO getInfoRoom(Room room) {
        Set<String> teamName = new HashSet<>();
        List<Team> teams = Optional.ofNullable(room.getTeams()).orElseGet(ArrayList::new);

        List<TeamDTO> teamDTOList = new ArrayList<>();
        for (Team team : teams) {
            if (teamName.contains(team.getTeamName())) continue;
            teamName.add(team.getTeamName());

            List<UserTeamRels> userTeamRelsList = userTeamRelsRepository.findAllByTeam(team);
            List<UserDTO> userDTOList = new ArrayList<>();

            for (UserTeamRels userTeamRels : userTeamRelsList) {
                User user = userTeamRels.getUser();
                userDTOList.add(new UserDTO(userTeamRels.isCaptain(), user.getUserName()));
            }

            teamDTOList.add(new TeamDTO(team.getTeamName(), userDTOList));
        }

        return new RoomDTO(room.getRoomRef(), teamDTOList);
    }

    private List<Room> getRoomsForUser(RequestContext requestContext) {
        User user = userRepository.getOne(requestContext.getUserId());
        List<Room> rooms = roomRepository.findAll();
        List<Room> userRooms = new ArrayList<>();

        for (Room room : rooms) {
            boolean userInRoom = false;
            for (Team team : room.getTeams()) {
                if (userTeamRelsRepository.existsByUserAndTeam(user, team)) {
                    userInRoom = true;
                    break;
                }
            }

            if (!userInRoom) {
                userRooms.add(room);
            }
        }

        return userRooms;
    }

    @Autowired
    public RoomService(RoomRepository roomRepository,
                       UserTeamRelsRepository userTeamRelsRepository,
                       UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userTeamRelsRepository = userTeamRelsRepository;
        this.userRepository = userRepository;
    }

    public RoomDTO createRoom() {
        String roomRef = UUID.randomUUID().toString().toUpperCase().substring(0, 8);
        Room room = new Room(roomRef, "room_" + roomRef.substring(0, 3));

        roomRepository.save(room);
        return this.getInfoRoom(room);
    }

    public RoomDTO findRandRoom(RequestContext requestContext) {
        int amountRandRooms = this.defAmountRoomsForUser(requestContext);
        if (amountRandRooms < 1) {
            return null;
        }

        Random random = new Random();
        Room randRoom = this.getRoomsForUser(requestContext)
                                .get(random.nextInt(amountRandRooms));

        return this.getInfoRoom(randRoom);
    }

    public RoomDTO findRoom(String ref) {
        Room room = roomRepository.findByRoomRef(ref);
        if (room == null) {
            return null;
        }

        return this.getInfoRoom(room);
    }

    public List<RoomDTO> findAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::getInfoRoom).collect(Collectors.toList());
    }

    public List<RoomDTO> findAllRoomsForUser(RequestContext requestContext) {
        User user = userRepository.getOne(requestContext.getUserId());
        List<Team> teams = userTeamRelsRepository
                            .findAllByUser(user)
                            .stream()
                            .map(UserTeamRels::getTeam)
                            .collect(Collectors.toList());

        List<Room> rooms = teams
                            .stream()
                            .map(Team::getRoom)
                            .collect(Collectors.toList());

        return rooms.stream().map(this::getInfoRoom).collect(Collectors.toList());
    }

    public Integer defAmountRooms() {
        return roomRepository.countRooms();
    }

    public Integer defAmountRoomsForUser(RequestContext requestContext) {
        return this.getRoomsForUser(requestContext).size();
    }
}
