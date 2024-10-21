package com.sbm.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "user_interests")
public class UserInterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInterestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_id")
    private InterestEntity interest;
    
    // Getters and Setters
    public Long getUserInterestId() {
        return userInterestId;
    }

    public void setUserInterestId(Long userInterestId) {
        this.userInterestId = userInterestId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public InterestEntity getInterest() {
        return interest;
    }

    public void setInterest(InterestEntity interest) {
        this.interest = interest;
    }
}
