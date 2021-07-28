package com.netcracker.services;

import com.netcracker.controllers.CodeNamesExceptions;
import com.netcracker.dto.chat.MessageDTO;

import com.netcracker.dto.chat.MessageResponseDTO;
import com.netcracker.entities.*;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.RoomRepository;
import com.netcracker.repositories.UserTeamRelsRepository;
import com.netcracker.repositories.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Component
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserTokenRepository userTokenRepository;
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

    private UserTeamRels getUserTeamRels(User user, Room room) {
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
            UserTeamRelsRepository userTeamRelsRepository,
            RoomRepository roomRepository
    ) {
        this.userTokenRepository = userTokenRepository;
        this.chatRepository = chatRepository;
        this.userTeamRelsRepository = userTeamRelsRepository;
        this.roomRepository = roomRepository;
    }

    public MessageResponseDTO processMessage(MessageDTO messageDTO, String roomRef, String userToken) {
        User user = userTokenRepository.findByUserToken(userToken).getUser();
        Room room = roomRepository.findByRoomRef(roomRef);
        UserTeamRels userTeamRels = this.getUserTeamRels(user, room);

        Message message = Message.builder()
                        .user(user)
                        .room(room)
                        .userText(messageDTO.getUserText())
                        .createdOn(messageDTO.getCreatedOn())
                        .build();

        // chatRepository.save(message);
        return this.getMessageResponseDTO(messageDTO, userTeamRels);
    }

    public List<MessageResponseDTO> findChatMessages(String ref) {
        Room room = roomRepository.findByRoomRef(ref);
        List<Message> messages = chatRepository.findAllByRoom(room);

        List<MessageResponseDTO> messageResponseDTOList = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = MessageDTO.builder()
                                        .userText(message.getUserText())
                                        .createdOn(message.getCreatedOn())
                                        .build();

            User user = message.getUser();
            UserTeamRels userTeamRels = this.getUserTeamRels(user, room);

            messageResponseDTOList.add(this.getMessageResponseDTO(messageDTO, userTeamRels));
        }

        return messageResponseDTOList;
    }
}
