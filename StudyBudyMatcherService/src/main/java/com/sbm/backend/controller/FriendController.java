package com.sbm.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sbm.backend.model.FriendEntity;
import com.sbm.backend.model.UserEntity;
import com.sbm.backend.service.FriendService;
import com.sbm.backend.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/friends")
@CrossOrigin(origins = "*")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FriendEntity>> getFriends(@PathVariable Long userId) {
        List<FriendEntity> friends = friendService.findFriendsByUserId(userId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestBody Map<String, Long> requestData) {
        Long userId = requestData.get("userId");
        Long friendId = requestData.get("friendId");

        if (userId == null || friendId == null) {
            return ResponseEntity.badRequest().body("UserId and FriendId are required.");
        }

        UserEntity user = userService.findById(userId).orElse(null);
        UserEntity friend = userService.findById(friendId).orElse(null);

        if (user == null || friend == null) {
            return ResponseEntity.badRequest().body("User or Friend not found.");
        }

        // Create a new FriendEntity
        FriendEntity friendEntity = new FriendEntity();
        friendEntity.setUser(user);
        friendEntity.setFriend(friend);

        // Set the full names
        friendEntity.setUserFullName(user.getFullName());
        friendEntity.setFriendFullName(friend.getFullName());

        // Save the new friend
        FriendEntity newFriend = friendService.saveFriend(friendEntity);

        return ResponseEntity.ok(newFriend);
    }
}
