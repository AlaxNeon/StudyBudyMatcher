package com.sbm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.model.MessageEntity;
import com.sbm.backend.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageEntity> findMessagesByChatId(Long chatId) {
        return messageRepository.findByChatChatId(chatId);
    }

    public MessageEntity saveMessage(MessageEntity message) {
        return messageRepository.save(message);
    }
}
