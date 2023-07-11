package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.PieceTypeRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.PieceType;
import com.b2i.serviceorganisation.repository.PieceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PieceTypeServiceImplementation implements PieceTypeService {

    @Autowired
    private PieceTypeRepository pieceTypeRepository;


    // CREATE PIECE TYPE
    @Override
    public ResponseEntity<Object> createPieceType(PieceTypeRequest pieceTypeRequest) {

        // NEW PIECE TYPE
        PieceType type = new PieceType();

        try {
            if(pieceTypeRequest.getLabel() == null) {
                throw new Exception("Piece type label can't be null !");
            }

            type.setLabel(pieceTypeRequest.getLabel());
            return ResponseHandler.generateResponse("Piece type created !", HttpStatus.CREATED,
                    pieceTypeRepository.save(type));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL PIECE TYPES
    @Override
    public ResponseEntity<Object> findAllPieceTypes() {

        // GET ALL
        List<PieceType> pieceTypes = pieceTypeRepository.findAll();

        try {
            if(pieceTypes.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty !");
            }
            return ResponseHandler.generateResponse("Piece types list", HttpStatus.OK, pieceTypes);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE PIECE TYPE
    @Override
    public ResponseEntity<Object> updatePieceType(Long idPieceType, PieceTypeRequest pieceTypeRequest) {

        // GET PIECE TYPE
        Optional<PieceType> pieceType = pieceTypeRepository.findById(idPieceType);

        try {
            if(!pieceType.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Piece type not found !");
            }

            PieceType pt = pieceType.get();
            if(pieceTypeRequest.getLabel() != null) {
                pt.setLabel(pieceTypeRequest.getLabel());
            }
            return ResponseHandler.generateResponse("Piece type " + idPieceType + " has properly been updated !",
                    HttpStatus.OK, pieceTypeRepository.save(pt));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE PIECE TYPE
    @Override
    public ResponseEntity<Object> deletePieceType(Long idPieceType) {

        // GET PIECE TYPE
        Optional<PieceType> pieceType = pieceTypeRepository.findById(idPieceType);

        try {

            if(!pieceType.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Piece type not found !");
            }

            pieceTypeRepository.deleteById(idPieceType);
            return ResponseHandler.generateResponse("Piece type " + idPieceType + " has properly been deleted !",
                    HttpStatus.OK, null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND PIECE TYPE BY ID
    @Override
    public ResponseEntity<Object> findPieceTypeById(Long idPieceType) {

        // GET PIECE TYPE
        Optional<PieceType> pieceType = pieceTypeRepository.findById(idPieceType);

        try {
            return pieceType.map(p -> ResponseHandler.generateResponse("Piece type " + idPieceType, HttpStatus.OK, p))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Piece type not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findPieceTypeByLabel(String label) {

        try {
            List<PieceType> pieceTypes = pieceTypeRepository.findPieceTypeByLabel(label);
            if (pieceTypes.isEmpty()) {
                return ResponseHandler.generateResponse("PieceTypes list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("PieceTypes list", HttpStatus.OK, pieceTypes);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL
    @Override
    public Long countAll() {
        return pieceTypeRepository.count();
    }
}
