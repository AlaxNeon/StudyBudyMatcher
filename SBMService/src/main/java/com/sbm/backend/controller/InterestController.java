package com.sbm.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sbm.backend.model.InterestEntity;
import com.sbm.backend.service.InterestService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interests")
@CrossOrigin(origins = "*")
public class InterestController {

    @Autowired
    private InterestService interestService;

    @GetMapping("/")
    public ResponseEntity<List<InterestEntity>> getAllInterests() {
        List<InterestEntity> interests = interestService.findAllInterests();
        return ResponseEntity.ok(interests);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createInterest(@RequestBody InterestEntity interest) {
        Optional<InterestEntity> existingInterest = interestService.findByName(interest.getInterestName());
        if (existingInterest.isPresent()) {
            return ResponseEntity.badRequest().body("Interest already exists");
        }
        InterestEntity newInterest = interestService.saveInterest(interest);
        return ResponseEntity.ok(newInterest);
    }
}
