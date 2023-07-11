package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.dto.request.BeneficiaryRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.Beneficiary;
import com.b2i.serviceorganisation.model.PieceType;
import com.b2i.serviceorganisation.repository.BeneficiaryRepository;
import com.b2i.serviceorganisation.repository.PieceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryServiceImplementation implements BeneficiaryService {


    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private PieceTypeRepository pieceTypeRepository;


    // CREATE BENEFICIARY
    @Override
    public Beneficiary createBeneficiary(BeneficiaryRequest beneficiaryRequest, Long idPieceType) {

        // GET PIECE TYPE
        Optional<PieceType> pieceType = pieceTypeRepository.findById(idPieceType);

        try {

            // FIRSTNAME
            Utils.checkStringValues(beneficiaryRequest.getFirstName(), "First name");

            // LASTNAME
            Utils.checkStringValues(beneficiaryRequest.getLastName(), "Last name");

            // PHONE NUMBER
            // Utils.checkStringValues(beneficiaryRequest.getPhoneNumber(), "Phone number");

            // EMAIL
            // Utils.checkStringValues(beneficiaryRequest.getEmail(), "Email");

            // PIECE ID
            Utils.checkStringValues(beneficiaryRequest.getPieceId(), "Piece id");

            // BIRTHDATE
            if(beneficiaryRequest.getBirthDate() == null) { throw new Exception("Birthdate can't be null !"); }

            // PIECE TYPE
            if(!pieceType.isPresent()) { throw new Exception("Piece type not found !"); }

            // SETTING VALUES
            // NEW BENEFICIARY

            Beneficiary beneficiary = new Beneficiary(beneficiaryRequest.getFirstName(), beneficiaryRequest.getLastName(),
                    beneficiaryRequest.getPhoneNumber(), beneficiaryRequest.getEmail(), beneficiaryRequest.getBirthDate(),
                    pieceType.get(), beneficiaryRequest.getPieceId());

            // SAVE
            return beneficiaryRepository.save(beneficiary);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }


    // FIND ALL BENEFICIARIES
    @Override
    public ResponseEntity<Object> findAllBeneficiaries() {

        // GET ALL
        List<Beneficiary> beneficiaries = beneficiaryRepository.findAll();

        try {

            if(beneficiaries.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }
            return ResponseHandler.generateOkResponse("Beneficiaries list", beneficiaries);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE BENEFICIARY
    @Override
    public ResponseEntity<Object> updateBeneficiary(Long idBeneficiary, BeneficiaryRequest beneficiaryRequest) {
        return null;
    }


    // DELETE BENEFICIARY
    @Override
    public ResponseEntity<Object> deleteBeneficiary(Long idBeneficiary) {

        // GET BENEFICIARY
        Optional<Beneficiary> beneficiary = beneficiaryRepository.findById(idBeneficiary);

        try {
            if(!beneficiary.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Beneficiary not found !");
            }

            // DELETING
            beneficiaryRepository.deleteById(idBeneficiary);
            return ResponseHandler.generateOkResponse("Beneficiary " + idBeneficiary + " has properly been deleted !", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND BY ID
    @Override
    public ResponseEntity<Object> findBeneficiaryById(Long idBeneficiary) {

        // GET BENEFICIARY
        Optional<Beneficiary> beneficiary = beneficiaryRepository.findById(idBeneficiary);

        try {

            return beneficiary.map(b -> ResponseHandler.generateOkResponse("Beneficiary " + idBeneficiary, b))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Beneficiary not found !"));

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL
    @Override
    public Long countAllBeneficiaries() {
        return beneficiaryRepository.count();
    }
}
