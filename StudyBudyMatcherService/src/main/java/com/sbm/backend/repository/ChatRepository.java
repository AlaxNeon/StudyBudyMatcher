package com.sbm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbm.backend.model.ChatEntity;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    List<ChatEntity> findByUserOneUserIdOrUserTwoUserId(Long userOneId, Long userTwoId);
}
