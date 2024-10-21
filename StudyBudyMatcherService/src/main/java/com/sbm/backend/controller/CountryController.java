package com.sbm.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sbm.backend.model.CountryEntity;
import com.sbm.backend.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<List<CountryEntity>> getAllCountries() {
        List<CountryEntity> countries = countryService.getAllCountries(); // This method is now defined
        return ResponseEntity.ok(countries);
    }
}
