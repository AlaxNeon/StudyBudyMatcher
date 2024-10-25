CREATE TABLE chat_messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id BIGINT,
    receiver_id BIGINT,
    content TEXT,
    timestamp DATETIME
);

CREATE TABLE chats (
    chat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_one_id BIGINT,
    user_two_id BIGINT,
    created_at TIMESTAMP,
    message VARCHAR(255)
);

CREATE TABLE countries (
    country_id BIGINT PRIMARY KEY,
    country_name VARCHAR(255)
);

CREATE TABLE friends (
    friendship_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    friend_id BIGINT,
    created_at TIMESTAMP,
    friend_full_name VARCHAR(255),
    user_full_name VARCHAR(255)
);

CREATE TABLE interests (
    interest_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    interest_name VARCHAR(255)
);

CREATE TABLE messages (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message_text VARCHAR(255),
    sent_at DATETIME(6),
    chat_id BIGINT,
    sender_id BIGINT
);

CREATE TABLE profiles (
    profile_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    bio VARCHAR(255),
    country_id BIGINT,
    meetlink VARCHAR(255),
    meet_link VARCHAR(255)
);

CREATE TABLE user_interests (
    user_interest_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    interest_id BIGINT
);

CREATE TABLE user_tokens (
    token_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    token VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    created_at TIMESTAMP,
    meet_link VARCHAR(255)
);