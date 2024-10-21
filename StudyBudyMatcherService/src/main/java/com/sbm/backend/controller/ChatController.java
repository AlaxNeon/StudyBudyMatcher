package com.sbm.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import com.sbm.backend.model.ChatEntity;
import com.sbm.backend.model.ChatMessage;
import com.sbm.backend.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatEntity>> getUserChats(@PathVariable Long userId) {
        List<ChatEntity> chats = chatService.findChatsByUserId(userId);
        return ResponseEntity.ok(chats);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChat(@RequestBody ChatEntity chat) {
        chat.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        ChatEntity newChat = chatService.saveChat(chat);
        return ResponseEntity.ok(newChat);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ChatEntity chat) {
        chat.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        ChatEntity newChat = chatService.saveChat(chat);
        return ResponseEntity.ok(newChat);
    }
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }
}
