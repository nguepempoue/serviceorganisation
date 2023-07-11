package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.FamilySituationRequest;
import org.springframework.http.ResponseEntity;

public interface FamilySituationService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createFamilySituation(FamilySituationRequest familySituationRequest);

    ResponseEntity<Object> findAllFamilySituations();

    ResponseEntity<Object> updateFamilySituation(Long idFamilySituation, FamilySituationRequest familySituationRequest);

    ResponseEntity<Object> deleteFamilySituation(Long idFamilySituation);

    ResponseEntity<Object> findFamilySituationByLabel(String name);


    // MORE OPERATIONS //
    ResponseEntity<Object> findFamilySituationById(Long idFamilySituation);

    Long countAll();
}
