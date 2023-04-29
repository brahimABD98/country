package com.redshift.kountry.Country;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class CountryService implements ICountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> FindAll() {
        return countryRepository.findAll();
    }

    @Override
    public ResponseEntity<String> addOne(Country country) {
        try {
            countryRepository.save(country);
        } catch (Exception e) {
            String errorMessage = "Error while adding a country:\n" + e.getMessage();
            log.error(errorMessage, e);
            String jsonResponse = "{\"error\": \"" + e.getMessage() + "\"}";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
        }

        String message = "country added successfully:" + country;
        log.info(message);
        return ResponseEntity.ok(message);
    }

    @Override
    public ResponseEntity<String> addMany(List<Country> countries) {

        countries.parallelStream().forEach(this::addOne);
        log.info("countries {} added successfully", countries.size());
        return ResponseEntity.ok("Countries added successfully");
    }

    @Override
    public ResponseEntity<String> updateCountry(Country country) {
        try {
            countryRepository.save(country);
        } catch (Exception e) {
            String errorMessage = "Error while updating a country:\n" + e.getMessage();
            log.error(errorMessage, e);
            String jsonResponse = "{\"error\": \"" + e.getMessage() + "\"}";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
        }

        String message = "country updated successfully:" + country;
        log.info(message);
        return ResponseEntity.ok(message);
    }

    @Override
    public ResponseEntity<Set<Country>> findCoutrybyCity(String city) {
        try {
            Set<Country> res = countryRepository.findCountryByCity(city);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "error while fetching country";
            log.error(errorMessage, e);
            return new ResponseEntity<>(Collections.emptySet(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
