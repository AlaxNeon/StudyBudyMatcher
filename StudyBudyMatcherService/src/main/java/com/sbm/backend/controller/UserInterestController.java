package com.sbm.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sbm.backend.model.UserInterestEntity;
import com.sbm.backend.service.UserInterestService;

import java.util.List;

@RestController
@RequestMapping("/api/user-interests")
@CrossOrigin(origins = "*")
public class UserInterestController {
	

    @Autowired
    private UserInterestService userInterestService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserInterestEntity>> getUserInterests(@PathVariable Long userId) {
        List<UserInterestEntity> userInterests = userInterestService.findByUserId(userId);
        return ResponseEntity.ok(userInterests);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUserInterest(@RequestBody UserInterestEntity userInterest) {
        UserInterestEntity newUserInterest = userInterestService.saveUserInterest(userInterest);
        return ResponseEntity.ok(newUserInterest);
    }
    
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUserInterests(@PathVariable Long userId) {
        try {
            userInterestService.deleteUserInterestsByUserId(userId); // Your service method to delete interests
            return ResponseEntity.ok("User interests deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user interests.");
        }
    }
}
