package com.netcracker.services;

import com.netcracker.entities.Message;
import com.netcracker.dto.MessagesDTO;
import com.netcracker.repositories.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MessagesService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessagesService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessagesDTO getMessages(long id) {
        Message messages = messageRepository
                                .findById(id)
                                .orElseThrow(RuntimeException::new);

        return null;
    }
}
