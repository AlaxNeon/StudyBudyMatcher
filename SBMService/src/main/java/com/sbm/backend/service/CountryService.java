package com.sbm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbm.backend.exception.ResourceNotFoundException;
import com.sbm.backend.model.CountryEntity;
import com.sbm.backend.repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository; // Inject the repository

    // Method to get all countries
    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll(); // Use the repository to fetch all countries
    }

    public CountryEntity findById(Long countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + countryId));
    }
}
