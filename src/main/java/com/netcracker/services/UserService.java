package com.netcracker.services;

import com.netcracker.dto.RequestContext;
import com.netcracker.dto.RoleTeamDTO;
import com.netcracker.dto.UserDTO;
import com.netcracker.entities.*;
import com.netcracker.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Component
public class UserService {
    private final UserRepository userRepository;
    private final UserTeamRelsRepository userTeamRelsRepository;
    private final TeamRepository teamRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserTeamRelsRepository userTeamRelsRepository,
                       TeamRepository teamRepository,
                       RoomRepository roomRepository
    ) {
        this.userRepository = userRepository;
        this.userTeamRelsRepository = userTeamRelsRepository;
        this.teamRepository = teamRepository;
        this.roomRepository = roomRepository;
    }

    public UserDTO getUser(RequestContext requestContext) {
        User user = userRepository.getOne(requestContext.getUserId());

        return new UserDTO(
            user.getUserTeamRels().isCaptain(),
            user.getUserName()
        );
    }

    public UserDTO updateUser(RequestContext requestContext, RoleTeamDTO roleTeamDTO) {
        User user = userRepository.getOne(requestContext.getUserId());
        Room room = roomRepository.findByRoomRef(roleTeamDTO.getRoomRef());
        Team team = teamRepository.findByRoom(room);

        if (team == null) {
            teamRepository.save(new Team(room, roleTeamDTO.getTeamName(), 0));
        } else {
            team.setTeamName(roleTeamDTO.getTeamName());
            teamRepository.save(team);
        }

        roomRepository.save(room);
        userTeamRelsRepository.save(new UserTeamRels(user, team, roleTeamDTO.isCaptain()));

        return new UserDTO(
            user.getUserTeamRels().isCaptain(),
            user.getUserName()
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
