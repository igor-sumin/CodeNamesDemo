package com.netcracker.services;

import com.netcracker.controllers.CodeNamesExceptions;
import com.netcracker.dto.RequestContext;
import com.netcracker.dto.chat.MessageDTO;

import com.netcracker.dto.chat.MessageResponseDTO;
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
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserTokenRepository userTokenRepository;
    private final UserRepository userRepository;
    private final UserTeamRelsRepository userTeamRelsRepository;
    private final RoomRepository roomRepository;

    private MessageResponseDTO getMessageResponseDTO(MessageDTO messageDTO, UserTeamRels userTeamRels) {
        return MessageResponseDTO.builder()
                .info(messageDTO)
                .userName(userTeamRels.getUser().getUserName())
                .isCaptain(userTeamRels.isCaptain())
                .teamName(userTeamRels.getTeam().getTeamName())
                .build();
    }

    private UserTeamRels findUserTeamRels(User user, Room room) {
        Team team = room.getTeams()
                .stream()
                .filter(t -> userTeamRelsRepository.findByUserAndTeam(user, t) != null)
                .findAny()
                .orElseThrow(() -> new CodeNamesExceptions("can't find any team in room (" + room.getRoomRef() + ")"));

        return userTeamRelsRepository.findByUserAndTeam(user, team);
    }

    @Autowired
    public ChatService(
            ChatRepository chatRepository,
            UserTokenRepository userTokenRepository,
            UserRepository userRepository,
            UserTeamRelsRepository userTeamRelsRepository,
            RoomRepository roomRepository
    ) {
        this.userTokenRepository = userTokenRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.userTeamRelsRepository = userTeamRelsRepository;
        this.roomRepository = roomRepository;
    }

    public MessageResponseDTO processMessage(MessageDTO messageDTO, String ref, String token) {
        User user = userTokenRepository.findByUserToken(token).getUser();
        Room room = roomRepository.findByRoomRef(ref);
        UserTeamRels userTeamRels = this.findUserTeamRels(user, room);

        Message message = Message.builder()
                        .user(user)
                        .room(room)
                        .userText(messageDTO.getUserText())
                        .createdOn(messageDTO.getCreatedOn())
                        .build();

        chatRepository.save(message);
        return this.getMessageResponseDTO(messageDTO, userTeamRels);
    }

    public List<MessageResponseDTO> getChatAllMessages(String ref) {
        Room room = roomRepository.findByRoomRef(ref);
        List<Message> messages = Optional.ofNullable(chatRepository.findAllByRoom(room))
                                         .orElseGet(ArrayList::new);

        List<MessageResponseDTO> messageResponseDTOList = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = MessageDTO.builder()
                                        .userText(message.getUserText())
                                        .createdOn(message.getCreatedOn())
                                        .build();

            User user = message.getUser();
            UserTeamRels userTeamRels = this.findUserTeamRels(user, room);

            messageResponseDTOList.add(this.getMessageResponseDTO(messageDTO, userTeamRels));
        }

        return messageResponseDTOList;
    }

    public List<MessageResponseDTO> getChatTeamMessages(RequestContext requestContext, String ref) {
        User user = userRepository.getOne(requestContext.getUserId());
        Room room = roomRepository.findByRoomRef(ref);
        Team team = room.getTeams()
                .stream()
                .filter(t -> userTeamRelsRepository.findByUserAndTeam(user, t) != null)
                .findAny()
                .orElseThrow(() -> new CodeNamesExceptions("can't find any team in room (" + room.getRoomRef() + ")"));

        List<UserTeamRels> userTeamRelsList = userTeamRelsRepository.findAllByTeam(team);
        List<MessageResponseDTO> messageResponseDTOList = new ArrayList<>();

        for (UserTeamRels userTeamRels : userTeamRelsList) {
            User teammate = userTeamRels.getUser();

            List<Message> messages = chatRepository.findAllByRoomAndUser(room, teammate);
            for (Message message : messages) {
                MessageDTO messageDTO = MessageDTO.builder()
                                            .userText(message.getUserText())
                                            .createdOn(message.getCreatedOn())
                                            .build();

                messageResponseDTOList.add(this.getMessageResponseDTO(messageDTO, userTeamRels));
            }
        }

        return messageResponseDTOList;
    }
}
