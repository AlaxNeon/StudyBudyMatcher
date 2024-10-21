package com.sbm.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbm.backend.model.InterestEntity;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {
    Optional<InterestEntity> findByInterestName(String interestName);
}