package com.netcracker.controllers;

import com.netcracker.dto.RequestContext;
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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.netcracker.filters.EntryFilter.REQUEST_CONTEXT;

@Slf4j
@Controller
@RequestMapping("/chat")
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

    @GetMapping("/{roomRef}/history/all")
    public ResponseEntity<List<MessageResponseDTO>> findChatAllHistoryMessages(@PathVariable String roomRef) {
        return ResponseEntity.ok(chatService.getChatAllMessages(roomRef));
    }

    @GetMapping("/{roomRef}/history/team")
    public ResponseEntity<List<MessageResponseDTO>> findChatTeamHistoryMessages(
            @RequestAttribute(REQUEST_CONTEXT) RequestContext requestContext, @PathVariable String roomRef
    ) {
        return ResponseEntity.ok(chatService.getChatTeamMessages(requestContext, roomRef));
    }
}
