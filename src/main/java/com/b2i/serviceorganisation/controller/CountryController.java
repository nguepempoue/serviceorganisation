package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.CountryRequest;
import com.b2i.serviceorganisation.service.CountryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
@CrossOrigin("*")
public class CountryController {

    @Autowired
    private CountryServiceImplementation countryServiceImplementation;


    // CREATE
    @PostMapping
    public ResponseEntity<Object> createCountry(@RequestBody CountryRequest countryRequest) {
        return countryServiceImplementation.createCountry(countryRequest);
    }


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllCountries() {
        return countryServiceImplementation.findAllCountries();
    }


    // UPDATE
    @PutMapping("/{idCountry}")
    public ResponseEntity<Object> updateCountry(@PathVariable("idCountry") Long idCountry, @RequestBody CountryRequest countryRequest) {
        return countryServiceImplementation.updateCountry(idCountry, countryRequest);
    }


    // DELETE
    @DeleteMapping("/{idCountry}")
    public ResponseEntity<Object> deleteCountry(@PathVariable("idCountry") Long idCountry) {
        return countryServiceImplementation.deleteCountry(idCountry);
    }


    // FIND BY ID
    @GetMapping("/{idCountry}")
    public ResponseEntity<Object> findCountryByID(@PathVariable("idCountry") Long idCountry) {
        return countryServiceImplementation.findCountryByID(idCountry);
    }
}
