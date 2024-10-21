package com.sbm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.model.ChatEntity;
import com.sbm.backend.repository.ChatRepository;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<ChatEntity> findChatsByUserId(Long userId) {
        return chatRepository.findByUserOneUserIdOrUserTwoUserId(userId, userId);
    }

    public ChatEntity saveChat(ChatEntity chat) {
        return chatRepository.save(chat);
    }
}
