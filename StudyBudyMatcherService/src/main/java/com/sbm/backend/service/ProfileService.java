package com.sbm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.model.ProfileEntity;
import com.sbm.backend.repository.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Optional<ProfileEntity> findByUserId(Long userId) {
        return profileRepository.findByUserUserId(userId);
    }

    public ProfileEntity createOrUpdateProfile(ProfileEntity profile) {
        return profileRepository.save(profile);
    }
}
