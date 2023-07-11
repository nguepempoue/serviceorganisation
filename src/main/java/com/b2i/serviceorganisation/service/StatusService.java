package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.StatusRequest;
import org.springframework.http.ResponseEntity;

public interface StatusService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createStatus(StatusRequest statusRequest);

    ResponseEntity<Object> findAllStatus();

    ResponseEntity<Object> updateStatus(Long idStatus, StatusRequest statusRequest);

    ResponseEntity<Object> deleteStatus(Long idStatus);


    // MORE OPERATIONS //
    ResponseEntity<Object> findStatusById(Long idStatus);

    Long countAllStatus();
}
