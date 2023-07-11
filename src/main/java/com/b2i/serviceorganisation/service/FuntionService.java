package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.FunctionRequest;
import com.b2i.serviceorganisation.model.Function;
import org.springframework.http.ResponseEntity;

public interface FuntionService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createFunction(FunctionRequest functionRequest);

    ResponseEntity<Object> findAllFunctions();

    ResponseEntity<Object> updateFunction(Long idFunction, FunctionRequest functionRequest);

    ResponseEntity<Object> deleteFunction(Long idFunction);


    // MORE OPERATIONS //
    ResponseEntity<Object> findFunctionById(Long idFunction);

    Long countAll();

    Function saveFunction(Function function);
}
