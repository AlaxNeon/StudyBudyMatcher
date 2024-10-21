package com.sbm.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sbm.backend.model.ProfileEntity;
import com.sbm.backend.service.ProfileService;

import java.util.Map; // Correct import for Map
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins="*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable Long userId) {
        Optional<ProfileEntity> profile = profileService.findByUserId(userId);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileEntity profile) {
        ProfileEntity createdProfile = profileService.createOrUpdateProfile(profile);
        return ResponseEntity.ok(createdProfile);
    }
}
