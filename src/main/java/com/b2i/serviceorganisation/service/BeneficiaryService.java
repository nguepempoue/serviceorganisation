package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.BeneficiaryRequest;
import com.b2i.serviceorganisation.model.Beneficiary;
import org.springframework.http.ResponseEntity;

public interface BeneficiaryService {

    // CRUD OPERATIONS //
    Beneficiary createBeneficiary(BeneficiaryRequest beneficiaryRequest, Long idPieceType);

    ResponseEntity<Object> findAllBeneficiaries();

    ResponseEntity<Object> updateBeneficiary(Long idBeneficiary, BeneficiaryRequest beneficiaryRequest);

    ResponseEntity<Object> deleteBeneficiary(Long idBeneficiary);


    // MORE OPERATIONS //
    ResponseEntity<Object> findBeneficiaryById(Long idBeneficiary);

    Long countAllBeneficiaries();
}
