package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.CivilityRequest;
import com.b2i.serviceorganisation.service.CivilityServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/civilities")
@CrossOrigin("*")
public class CivilityController {


    @Autowired
    private CivilityServiceImplementation civilityServiceImplementation;


    // CREATE
    @PostMapping
    public ResponseEntity<Object> createCivility(@RequestBody CivilityRequest civilityRequest) {
        return civilityServiceImplementation.createCivility(civilityRequest);
    }


    // READ
    @GetMapping
    public ResponseEntity<Object> findAllCivilities() {
        return civilityServiceImplementation.findAllCivilities();
    }


    // UPDATE
    @PutMapping("/{idCivility}")
    public ResponseEntity<Object> updateCivility(@PathVariable("idCivility") Long idCivility, @RequestBody CivilityRequest civilityRequest) {
        return civilityServiceImplementation.updateCivility(idCivility, civilityRequest);
    }


    // DELETE
    @DeleteMapping("/{idCivility}")
    public ResponseEntity<Object> deleteCivility(@PathVariable("idCivility") Long idCivility) {
        return civilityServiceImplementation.deleteCivility(idCivility);
    }


    // FIND BY ID
    @GetMapping("/{idCivility}")
    public ResponseEntity<Object> findCivilityById(@PathVariable("idCivility") Long idCivility) {
        return civilityServiceImplementation.findCivilityById(idCivility);
    }

    @GetMapping("search")
    public ResponseEntity<Object> findCivilityByName(@RequestParam String name) {
        return civilityServiceImplementation.findCivilityByName(name);
    }
}
