package com.redshift.kountry.Country;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/country")
public class CountryController {
    private final ICountryService countryService;

    @GetMapping("")
    public List<Country> findAll(){
        return countryService.FindAll();
    }

    @PostMapping("")
    public ResponseEntity<String> add(Country country){
       return countryService.addOne(country);
    }

    @PostMapping("/addMany")
    public ResponseEntity<String> addMany(@RequestBody List<Country> countries){

        return countryService.addMany(countries);
    }


}
