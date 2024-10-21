package com.sbm.backend.dto;
public class InterestDTO {
    private Long interestId;
    private String interestName;

    // Constructor
    public InterestDTO() {}

    public InterestDTO(Long interestId, String interestName) {
        this.interestId = interestId;
        this.interestName = interestName;
    }

    // Getters and Setters
    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }
}
