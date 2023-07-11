package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.UserTypeRequest;
import org.springframework.http.ResponseEntity;

public interface UserTypeService {
    //CREATE THE TYPE OF USER
    ResponseEntity<Object> createType(UserTypeRequest userTypeRequest);
    //DELETE THE TYPE OF USER
    ResponseEntity<Object> deleteType(Long id);
    //UPDATE THE TYPE OF USER
    ResponseEntity<Object> updateType(UserTypeRequest userTypeRequest, Long id);
    // FIND ALL USER'S TYPE
    ResponseEntity<Object> findAllTypes();
    //FIND THE TYPE BY ID
    ResponseEntity<Object> findTypeById(Long id);

    ResponseEntity<Object> findUserTypeByLabel(String label);

    Long countAllUserTypes();
}
