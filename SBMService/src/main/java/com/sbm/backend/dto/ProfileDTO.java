package com.sbm.backend.dto;

public class ProfileDTO {
    private Long profileId;
    private String bio;
    private String location;

    // Constructor
    public ProfileDTO() {}

    public ProfileDTO(Long profileId, String bio, String location) {
        this.profileId = profileId;
        this.bio = bio;
        this.location = location;
    }

    // Getters and Setters
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
