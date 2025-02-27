package com.sbm.backend.dto;

import java.util.List;

public class UserRegistrationDTO {
    private String fullName;
    private String email;
    private String password;
    private List<Long> interests; 
    private Long countryId; 
    private String bio; 
    private String meetLink; // For meeting link

    // Getter and Setter for meetLink
    public String getMeetLink() {
        return meetLink;
    }

    public void setMeetLink(String meetLink) {
        this.meetLink = meetLink;
    }

    // Other getters and setters...
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getInterests() {
        return interests;
    }

    public void setInterests(List<Long> interests) {
        this.interests = interests;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
