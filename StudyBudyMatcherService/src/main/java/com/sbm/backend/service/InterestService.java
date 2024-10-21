package com.sbm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.model.InterestEntity;
import com.sbm.backend.repository.InterestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InterestService {

    @Autowired
    private InterestRepository interestRepository;

    public Optional<InterestEntity> findByName(String name) {
        return interestRepository.findByInterestName(name);
    }

    public InterestEntity saveInterest(InterestEntity interest) {
        return interestRepository.save(interest);
    }

    public List<InterestEntity> findAllInterests() {
        return interestRepository.findAll();
    }
}
