package com.netcracker.services;

import com.netcracker.controllers.CodeNamesExceptions;
import com.netcracker.dto.*;
import com.netcracker.entities.*;
import com.netcracker.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Component
public class UserService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRelsRepository userTeamRelsRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoomRepository roomRepository,
                       TeamRepository teamRepository,
                       UserTeamRelsRepository userTeamRelsRepository
    ) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.teamRepository = teamRepository;
        this.userTeamRelsRepository = userTeamRelsRepository;
    }

    public UserDTO getUser(RequestContext requestContext, String ref) {
        User user = userRepository.getOne(requestContext.getUserId());
        Room room = roomRepository.findByRoomRef(ref);
        Team team = room.getTeams()
                            .stream()
                            .filter(t -> userTeamRelsRepository.findByUserAndTeam(user, t) != null)
                            .findAny()
                            .orElseThrow(() -> new CodeNamesExceptions("can't find any team in room (" + ref + ")"));

        UserTeamRels userTeamRels = userTeamRelsRepository.findByUserAndTeam(user, team);

        return new UserDTO(
                userTeamRels.isCaptain(),
                user.getUserName()
        );
    }

    public List<UserInfoDTO> getUserInfo(RequestContext requestContext) {
        User user = userRepository.getOne(requestContext.getUserId());
        List<UserTeamRels> userTeamRelsList = userTeamRelsRepository.findAllByUser(user);

        List<UserInfoDTO> userInfoDTOList = new ArrayList<>();
        for (UserTeamRels userTeamRels : userTeamRelsList) {
            Team team = userTeamRels.getTeam();
            Room room = team.getRoom();

            userInfoDTOList.add(
                UserInfoDTO.builder()
                    .roomRef(room.getRoomRef())
                    .teamName(team.getTeamName())
                    .isCaptain(userTeamRels.isCaptain())
                .build()
            );
        }

        return userInfoDTOList;
    }

    public UserDTO updateUser(RequestContext requestContext, RoleTeamDTO roleTeamDTO) {
        User user = userRepository.getOne(requestContext.getUserId());
        Room room = roomRepository.findByRoomRef(roleTeamDTO.getRoomRef());

        Team team = Optional.ofNullable(
                teamRepository.findByTeamNameAndRoom(roleTeamDTO.getTeamName(), room)
        ).orElseGet(() -> new Team(room, roleTeamDTO.getTeamName(), 0));

        if (roleTeamDTO.isCaptain() && userTeamRelsRepository.existsByCaptain(team.getTeamId())) {
            return null;
        }

        teamRepository.save(team);
        roomRepository.save(room);
        userTeamRelsRepository.save(new UserTeamRels(user, team, roleTeamDTO.isCaptain()));

        return new UserDTO(
                roleTeamDTO.isCaptain(),
                user.getUserName()
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
