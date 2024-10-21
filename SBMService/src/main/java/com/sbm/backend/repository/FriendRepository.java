package com.sbm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbm.backend.model.FriendEntity;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    List<FriendEntity> findByUser_UserId(Long userId);
}
