package com.sbm.backend.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "chats")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @ManyToOne
    @JoinColumn(name = "user_one_id", referencedColumnName = "userId")
    private UserEntity userOne;

    @ManyToOne
    @JoinColumn(name = "user_two_id", referencedColumnName = "userId")
    private UserEntity userTwo;

    private String message;

    @Column(updatable = false)
    private Timestamp createdAt;

    // Getters and Setters
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public UserEntity getUserOne() {
        return userOne;
    }

    public void setUserOne(UserEntity userOne) {
        this.userOne = userOne;
    }

    public UserEntity getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserEntity userTwo) {
        this.userTwo = userTwo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
