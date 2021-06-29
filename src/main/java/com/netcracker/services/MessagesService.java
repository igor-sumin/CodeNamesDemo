package com.netcracker.services;

import com.netcracker.entities.Messages;
import com.netcracker.dto.MessagesDTO;
import com.netcracker.repositories.MessagesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MessagesService {
    private final MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public MessagesDTO getMessages(long id) {
        Messages messages = messagesRepository
                                .findById(id)
                                .orElseThrow(RuntimeException::new);

        return new MessagesDTO(
                   messages.getUserText(), messages.getWired(), messages.getCreatedOn(), messages.getUserId()
        );
    }
}
