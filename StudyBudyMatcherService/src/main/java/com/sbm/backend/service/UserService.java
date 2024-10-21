package com.sbm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.model.InterestEntity;
import com.sbm.backend.model.UserEntity;
import com.sbm.backend.model.UserInterestEntity;
import com.sbm.backend.repository.InterestRepository;
import com.sbm.backend.repository.UserInterestRepository;
import com.sbm.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InterestRepository interestRepository;  // Add this line

    @Autowired
    private UserInterestRepository userInterestRepository;
    
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByFullName(String fullName) {
        return userRepository.existsByFullName(fullName);
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
    
    public void saveUserInterests(UserEntity user, List<Long> interestIds) {
        for (Long interestId : interestIds) {
            InterestEntity interest = interestRepository.findById(interestId).orElse(null);
            if (interest != null) {
                UserInterestEntity userInterest = new UserInterestEntity();
                userInterest.setUser(user);
                userInterest.setInterest(interest);
                userInterestRepository.save(userInterest);
            }
        }
    }
}
