package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.service.BeneficiaryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficiaries")
@CrossOrigin("*")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryServiceImplementation beneficiaryServiceImplementation;


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllBeneficiaries() {
        return beneficiaryServiceImplementation.findAllBeneficiaries();
    }


    // DELETE
    @DeleteMapping("/{idBeneficiary}")
    public ResponseEntity<Object> deleteBeneficiary(@PathVariable("idBeneficiary") Long idBeneficiary) {
        return beneficiaryServiceImplementation.deleteBeneficiary(idBeneficiary);
    }


    // FIND BY ID
    @GetMapping("/{idBeneficiary}")
    public ResponseEntity<Object> findBeneficiaryById(@PathVariable("idBeneficiary") Long idBeneficiary) {
        return beneficiaryServiceImplementation.findBeneficiaryById(idBeneficiary);
    }
}
