package com.netcracker.services;

import com.netcracker.dto.RequestContext;
import com.netcracker.dto.RoleTeamDTO;
import com.netcracker.dto.UserDTO;
import com.netcracker.entities.UserToken;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import com.netcracker.repositories.UserTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserService {
    private final UserRepository userRepository;
    private final UserTokensRepository userTokensRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserTokensRepository userTokensRepository) {
        this.userRepository = userRepository;
        this.userTokensRepository = userTokensRepository;
    }

    public UserDTO getUser(String token) {
        UserToken userTokens = userTokensRepository.findByUserToken(token);
        User users = userTokens.getUser();

        return new UserDTO(
                users.getUserTeamRels().isCaptain(),
                users.getUserName(),
                users.getUserTeamRels().getTeam().getTeamName()
        );
    }

    public User updateUser(RequestContext requestContext, RoleTeamDTO roleTeamDTO) {
        User user = userRepository.getOne(requestContext.getUserId());

        user.getUserTeamRels().setCaptain(roleTeamDTO.isCaptain());
        user.getUserTeamRels().getTeam().setTeamName(roleTeamDTO.getTeamName());
        userRepository.save(user);

        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
