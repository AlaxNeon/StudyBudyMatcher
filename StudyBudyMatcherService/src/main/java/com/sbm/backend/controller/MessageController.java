package com.sbm.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sbm.backend.model.MessageEntity;
import com.sbm.backend.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageEntity>> getChatMessages(@PathVariable Long chatId) {
        List<MessageEntity> messages = messageService.findMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageEntity message) {
        MessageEntity newMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(newMessage);
    }
}
