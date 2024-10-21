package com.sbm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.model.UserInterestEntity;
import com.sbm.backend.repository.UserInterestRepository;

import java.util.List;

@Service
public class UserInterestService {

    @Autowired
    private UserInterestRepository userInterestRepository;

    public List<UserInterestEntity> findByUserId(Long userId) {
        return userInterestRepository.findByUserUserId(userId);
    }

    public UserInterestEntity saveUserInterest(UserInterestEntity userInterest) {
        return userInterestRepository.save(userInterest);
    }
    
    
    public void deleteUserInterestsByUserId(Long userId) {
        List<UserInterestEntity> interests = userInterestRepository.findByUserUserId(userId);
        userInterestRepository.deleteAll(interests); // Delete all user interests for the user
    }

}
