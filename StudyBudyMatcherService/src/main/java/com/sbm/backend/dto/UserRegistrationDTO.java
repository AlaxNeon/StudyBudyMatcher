package com.sbm.backend.dto;

import java.util.List;

public class UserRegistrationDTO {
    private String fullName;
    private String email;
    private String password;
    private List<Long> interests; // List of interest IDs
    private Long countryId; // Change to Long for a single country ID
    private String bio; // Field to hold bio

    // Getters and Setters
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
    
    public Long getCountryId() { // Getter for countryId
        return countryId;
    }

    public void setCountryId(Long countryId) { // Setter for countryId
        this.countryId = countryId;
    }

    public String getBio() { // Getter for bio
        return bio;
    }

    public void setBio(String bio) { // Setter for bio
        this.bio = bio;
    }
}
