package com.sbm.backend.dto;

public class FriendDTO {
	private Long userId;
    private Long friendId;
    private String fullName;

    // Constructor
    public FriendDTO() {}

    public FriendDTO(Long friendId, Long userId, String fullName) {
        this.friendId = friendId;
        this.userId = userId;
        this.fullName = fullName;
    }

    // Getters and Setters
    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
    
    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
