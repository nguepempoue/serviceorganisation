package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.PieceTypeRequest;
import com.b2i.serviceorganisation.service.PieceTypeServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/piece-types")
@CrossOrigin("*")
public class PieceTypeController {

    @Autowired
    private PieceTypeServiceImplementation pieceTypeServiceImplementation;


    // CREATE
    @PostMapping
    public ResponseEntity<Object> createPieceType(PieceTypeRequest pieceTypeRequest) {
        return pieceTypeServiceImplementation.createPieceType(pieceTypeRequest);
    }


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllPieceTypes() {
        return pieceTypeServiceImplementation.findAllPieceTypes();
    }


    // UPDATE
    @PutMapping("/{idPieceType}")
    public ResponseEntity<Object> updatePieceType(@PathVariable("idPieceType") Long idPieceType, @RequestBody PieceTypeRequest pieceTypeRequest) {
        return pieceTypeServiceImplementation.updatePieceType(idPieceType, pieceTypeRequest);
    }


    // DELETE
    @DeleteMapping("/{idPieceType}")
    public ResponseEntity<Object> deletePieceType(@PathVariable("idPieceType") Long idPieceType) {
        return pieceTypeServiceImplementation.deletePieceType(idPieceType);
    }


    // FIND BY ID
    @GetMapping("{idPieceType}")
    public ResponseEntity<Object> findPieceTypeById(@PathVariable("idPieceType") Long idPieceType) {
        return pieceTypeServiceImplementation.findPieceTypeById(idPieceType);
    }

    @GetMapping("search")
    public ResponseEntity<Object> findPieceTypeByLabel(@RequestParam String label) {
        return pieceTypeServiceImplementation.findPieceTypeByLabel(label);
    }
}
