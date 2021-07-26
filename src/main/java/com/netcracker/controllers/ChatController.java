package com.netcracker.controllers;

import com.netcracker.dto.MessageDTO;
import com.netcracker.services.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor accessor) {
        chatService.saveMessage(messageDTO, accessor.getNativeHeader("token"));
    }

    @GetMapping("/messages/{roomRef}")
    public ResponseEntity<List<MessageDTO>> findChatMessages(@PathVariable String roomRef) {
        return ResponseEntity.ok(chatService.findChatMessages(roomRef));
    }
}
