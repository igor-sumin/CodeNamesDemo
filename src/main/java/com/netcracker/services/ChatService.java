package com.netcracker.services;

import com.netcracker.dto.MessageDTO;

import com.netcracker.entities.Message;
import com.netcracker.entities.Room;
import com.netcracker.entities.User;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.RoomRepository;
import com.netcracker.repositories.UserRepository;
import com.netcracker.repositories.UserTokensRepository;
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
    private final UserTokensRepository userTokensRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserTokensRepository userTokensRepository, RoomRepository roomRepository) {
        this.userTokensRepository = userTokensRepository;
        this.chatRepository = chatRepository;
        this.roomRepository = roomRepository;
    }

    public void saveMessage(MessageDTO messageDTO, String roomRef, String userToken) {
        Message message = Message.builder()
                .user(userTokensRepository.findByUserToken(userToken).getUser())
                .room(roomRepository.findByRoomRef(roomRef))
                .userText(messageDTO.getUserText())
                .createdOn(messageDTO.getCreatedOn())
                .build();

        // chatRepository.save(message);
    }

    public List<MessageDTO> findChatMessages(String ref) {
        Room room = roomRepository.findByRoomRef(ref);
        List<Message> messages = chatRepository.findAllByRoom(room);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (Message message : messages) {
            messageDTOList.add(
                    MessageDTO.builder()
                            .userText(message.getUserText())
                            .createdOn(message.getCreatedOn())
                            .build()
            );
        }

        return messageDTOList;
    }
}
