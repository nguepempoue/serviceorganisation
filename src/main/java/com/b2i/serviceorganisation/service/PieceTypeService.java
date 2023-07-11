package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.PieceTypeRequest;
import org.springframework.http.ResponseEntity;

public interface PieceTypeService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createPieceType(PieceTypeRequest pieceTypeRequest);

    ResponseEntity<Object> findAllPieceTypes();

    ResponseEntity<Object> updatePieceType(Long idPieceType, PieceTypeRequest pieceTypeRequest);

    ResponseEntity<Object> deletePieceType(Long idPieceType);

    ResponseEntity<Object> findPieceTypeByLabel(String name);


    // MORE OPERATIONS //
    ResponseEntity<Object> findPieceTypeById(Long idPieceType);

    Long countAll();
}
