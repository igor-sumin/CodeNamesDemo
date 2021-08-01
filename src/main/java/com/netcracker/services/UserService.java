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

@Slf4j
@Service
@Component
public class UserService {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final RoomRepository roomRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRelsRepository userTeamRelsRepository;

    private User getUser(RequestContext requestContext) {
        return userRepository.getOne(requestContext.getUserId());
    }

    private List<UserInfoDTO> getFullInfoUser(User user) {
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

    @Autowired
    public UserService(UserRepository userRepository,
                       UserTokenRepository userTokenRepository,
                       RoomRepository roomRepository,
                       TeamRepository teamRepository,
                       UserTeamRelsRepository userTeamRelsRepository
    ) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
        this.roomRepository = roomRepository;
        this.teamRepository = teamRepository;
        this.userTeamRelsRepository = userTeamRelsRepository;
    }

    public UserDTO getUserInRoom(RequestContext requestContext, String ref) {
        User user = this.getUser(requestContext);
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

    // TODO: что с userteamrels, messages?
    public void deleteUser(String userLogin) {
        User user = Optional.ofNullable(userRepository.findByUserLogin(userLogin))
                            .orElseThrow(() -> new CodeNamesExceptions("Not found user."));
        UserToken userToken = user.getUserToken();

        userTokenRepository.delete(userToken);
        userRepository.delete(user);
    }

    public List<UserInfoDTO> getUserWithNameInRoom(String userName) {
        User user = userRepository.findByUserName(userName);
        return this.getFullInfoUser(user);
    }

    public List<UserInfoDTO> getUserInfo(RequestContext requestContext) {
        User user = this.getUser(requestContext);
        return this.getFullInfoUser(user);
    }

    public UserDTO updateUser(RequestContext requestContext, RoleTeamDTO roleTeamDTO) {
        User user = this.getUser(requestContext);
        Room room = roomRepository.findByRoomRef(roleTeamDTO.getRoomRef());

        Team team = Optional.ofNullable(
                teamRepository.findByTeamNameAndRoom(roleTeamDTO.getTeamName(), room)
        ).orElseGet(() -> new Team(room, roleTeamDTO.getTeamName(), 0));
        teamRepository.save(team);

        if (roleTeamDTO.isCaptain() && userTeamRelsRepository.existsByCaptain(team.getTeamId())) {
            return null;
        }

        roomRepository.save(room);
        userTeamRelsRepository.save(new UserTeamRels(user, team, roleTeamDTO.isCaptain()));

        return new UserDTO(
                roleTeamDTO.isCaptain(),
                user.getUserName()
        );
    }

    public void logoutUser(RequestContext requestContext) {
        User user = this.getUser(requestContext);

        UserToken userToken = user.getUserToken();
        userToken.setUserToken("");
        userTokenRepository.save(userToken);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
