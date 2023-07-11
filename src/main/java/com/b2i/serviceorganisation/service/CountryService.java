package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.CountryRequest;
import org.springframework.http.ResponseEntity;

public interface CountryService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createCountry(CountryRequest countryRequest);

    ResponseEntity<Object> findAllCountries();

    ResponseEntity<Object> updateCountry(Long idCountry, CountryRequest countryRequest);

    ResponseEntity<Object> deleteCountry(Long idCountry);


    // MORE OPERATIONS //
    ResponseEntity<Object> findCountryByID(Long idCountry);

    Long countAll();
}
