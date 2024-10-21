package com.sbm.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.model.FriendEntity;
import com.sbm.backend.repository.FriendRepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public FriendEntity saveFriend(FriendEntity friendEntity) {
        return friendRepository.save(friendEntity);
    }

    public List<FriendEntity> findFriendsByUserId(Long userId) {
        return friendRepository.findByUser_UserId(userId);
    }
}
