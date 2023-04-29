package com.redshift.kountry.Country;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ICountryService {
    public List<Country> FindAll();

    //CREATE
    public ResponseEntity<String> addOne(Country country);
    public ResponseEntity<String> addMany(List<Country> countries);

    //UPDATE
    public ResponseEntity<String> updateCountry(Country country);

    //READ
    public ResponseEntity<Set<Country>> findCoutrybyCity(String city);
}
