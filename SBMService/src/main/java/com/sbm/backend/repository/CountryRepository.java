package com.sbm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbm.backend.model.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
