package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.CivilityRequest;
import org.springframework.http.ResponseEntity;

public interface CivilityService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createCivility(CivilityRequest civilityRequest);

    ResponseEntity<Object> findAllCivilities();

    ResponseEntity<Object> updateCivility(Long idCivility, CivilityRequest civilityRequest);

    ResponseEntity<Object> deleteCivility(Long idCivility);

    ResponseEntity<Object> findCivilityByName(String name);


    // MORE OPERATIONS //
    ResponseEntity<Object> findCivilityById(Long idCivility);

    Long countAll();
}
