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

    public void saveMessage(MessageDTO messageDTO, List<String> accessor) {
        User user = userTokensRepository.findByUserToken(accessor.get(0)).getUser();
        Room room = roomRepository.findByRoomRef(messageDTO.getRoomRef());

        chatRepository.save(
                Message.builder()
                        .user(user)
                        .room(room)
                        .userText(messageDTO.getUserText())
                        .createdOn(messageDTO.getCreatedOn())
                        .build()
        );
    }

    public List<MessageDTO> findChatMessages(String ref) {
        Room room = roomRepository.findByRoomRef(ref);
        List<Message> messages = chatRepository.findAllByRoom(room);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (Message message : messages) {
            messageDTOList.add(
                    MessageDTO.builder()
                            .roomRef(ref)
                            .userText(message.getUserText())
                            .createdOn(message.getCreatedOn())
                            .build()
            );
        }

        return messageDTOList;
    }
}
