package com.sbm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbm.backend.model.UserInterestEntity;

import java.util.List;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterestEntity, Long> {
    List<UserInterestEntity> findByUserUserId(Long userId);
}
