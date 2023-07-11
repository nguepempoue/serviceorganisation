package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.FamilySituationRequest;
import com.b2i.serviceorganisation.service.FamilySituationServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family-situations")
@CrossOrigin("*")
public class FamilySituationController {

    @Autowired
    private FamilySituationServiceImplementation familySituationServiceImplementation;


    // CREATE
    @PostMapping
    public ResponseEntity<Object> createFamilySituation(@RequestBody FamilySituationRequest familySituationRequest) {
        return familySituationServiceImplementation.createFamilySituation(familySituationRequest);
    }


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllFamilySituations() {
        return familySituationServiceImplementation.findAllFamilySituations();
    }


    // UPDATE
    @PutMapping("/{idFamilySituation}")
    public ResponseEntity<Object> updateFamilySituation(@PathVariable("idFamilySituation") Long idFamilySituation, @RequestBody FamilySituationRequest familySituationRequest) {
        return familySituationServiceImplementation.updateFamilySituation(idFamilySituation, familySituationRequest);
    }


    // DELETE
    @DeleteMapping("/{idFamilySituation}")
    public ResponseEntity<Object> deleteFamilySituation(@PathVariable("idFamilySituation") Long idFamilySituation) {
        return familySituationServiceImplementation.deleteFamilySituation(idFamilySituation);
    }


    // FIND BY ID
    @GetMapping("/{idFamilySituation}")
    public ResponseEntity<Object> findFamilySituationById(@PathVariable("idFamilySituation") Long idFamilySituation) {
        return familySituationServiceImplementation.findFamilySituationById(idFamilySituation);
    }

    @GetMapping("search")
    public ResponseEntity<Object> findFamilySituationByLabel(@RequestParam String label) {
        return familySituationServiceImplementation.findFamilySituationByLabel(label);
    }
}
