package com.netcracker.controllers;

import com.netcracker.dto.chat.MessageDTO;
import com.netcracker.dto.chat.MessageResponseDTO;
import com.netcracker.services.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageDTO messageDTO, @Header String roomRef, @Header String token) {
        MessageResponseDTO messageResponseDTO = chatService.processMessage(messageDTO, roomRef, token);

        messagingTemplate.convertAndSendToUser(roomRef, "/messages", messageResponseDTO);
    }

    @GetMapping("/chat/{roomRef}/history")
    public ResponseEntity<List<MessageResponseDTO>> findChatMessages(@PathVariable String roomRef) {
        return ResponseEntity.ok(chatService.findChatMessages(roomRef));
    }
}
