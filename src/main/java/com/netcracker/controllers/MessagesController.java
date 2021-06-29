package com.netcracker.controllers;

import com.netcracker.dto.MessagesDTO;
import com.netcracker.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat")
public class MessagesController {
    private final MessagesService messagesServer;

    @Autowired
    public MessagesController(MessagesService messagesServer) {
        this.messagesServer = messagesServer;
    }

    @GetMapping("")
    public MessagesDTO getMessages(long id) {
        return messagesServer.getMessages(id);
    }
}
