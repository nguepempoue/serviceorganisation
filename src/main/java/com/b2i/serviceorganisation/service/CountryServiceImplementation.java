package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.dto.request.CountryRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.Country;
import com.b2i.serviceorganisation.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryService {

    @Autowired
    private CountryRepository countryRepository;


    // CREATE
    @Override
    public ResponseEntity<Object> createCountry(CountryRequest countryRequest) {

        // NEW COUNTRY
        Country country = new Country();

        try {

            // CHECK NAME
            Utils.checkStringValues(countryRequest.getName(), "Country name");

            country.setName(countryRequest.getName());
            return ResponseHandler.generateCreatedResponse("Country created !", countryRepository.save(country));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL COUNTRIES
    @Override
    public ResponseEntity<Object> findAllCountries() {

        // GET ALL
        List<Country> countries = countryRepository.findAll();

        try {

            if(countries.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }
            return ResponseHandler.generateOkResponse("Countries list", countries);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE
    @Override
    public ResponseEntity<Object> updateCountry(Long idCountry, CountryRequest countryRequest) {

        // GET COUNTRY
        Optional<Country> country = countryRepository.findById(idCountry);
        try {

            return country.map(c -> {

                if(countryRequest.getName() != null) { c.setName(countryRequest.getName()); }

                return ResponseHandler.generateOkResponse("Country " + idCountry + " has properly been updated !",
                        countryRepository.save(c));

            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Country not found !"));

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // COUNTRY
    @Override
    public ResponseEntity<Object> deleteCountry(Long idCountry) {

        // GET COUNTRY
        Optional<Country> country = countryRepository.findById(idCountry);

        try {

            if(!country.isPresent()) { return ResponseHandler.generateNotFoundResponse("Country not found !"); }
            countryRepository.deleteById(idCountry);
            return ResponseHandler.generateOkResponse("Country " + idCountry + " has properly been deleted !", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND COUNTRY BY ID
    @Override
    public ResponseEntity<Object> findCountryByID(Long idCountry) {

        // GET COUNTRY
        Optional<Country> country = countryRepository.findById(idCountry);

        try {

            return country.map(c -> ResponseHandler.generateOkResponse("Country " + idCountry, c))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Country not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL
    @Override
    public Long countAll() {
        return countryRepository.count();
    }
}
