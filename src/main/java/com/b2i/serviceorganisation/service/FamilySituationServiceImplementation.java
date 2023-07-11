package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.dto.request.FamilySituationRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.FamilySituation;
import com.b2i.serviceorganisation.repository.FamilySituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilySituationServiceImplementation implements FamilySituationService {

    @Autowired
    private FamilySituationRepository familySituationRepository;


    // CREATE FAMILY SITUATION
    @Override
    public ResponseEntity<Object> createFamilySituation(FamilySituationRequest familySituationRequest) {

        // NEW FAMILY SITUATION
        FamilySituation situation = new FamilySituation();

        try {

            // CHECK LABEL
            Utils.checkStringValues(familySituationRequest.getLabel(), "Family situation label");

            // SETTING VALUES
            situation.setLabel(familySituationRequest.getLabel());

            return ResponseHandler.generateCreatedResponse("Family situation created !",
                    familySituationRepository.save(situation));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL FAMILY SITUATIONS
    @Override
    public ResponseEntity<Object> findAllFamilySituations() {

        // GET ALL
        List<FamilySituation> situations = familySituationRepository.findAll();

        try {

            if(situations.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list");
            }
            return ResponseHandler.generateOkResponse("Family situations", situations);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE
    @Override
    public ResponseEntity<Object> updateFamilySituation(Long idFamilySituation, FamilySituationRequest familySituationRequest) {

        // GET FAMILY SITUATION
        Optional<FamilySituation> situation = familySituationRepository.findById(idFamilySituation);

        try {

            return situation.map(s -> {

                if(familySituationRequest.getLabel() != null) {
                    s.setLabel(familySituationRequest.getLabel());
                }
                return ResponseHandler.generateOkResponse("Family situation " + idFamilySituation + " " +
                        "has properly been updated !", familySituationRepository.save(s));

            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Situation not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE
    @Override
    public ResponseEntity<Object> deleteFamilySituation(Long idFamilySituation) {

        // GET
        Optional<FamilySituation> situation = familySituationRepository.findById(idFamilySituation);

        try {

            if(!situation.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Family situation not found !");
            }

            familySituationRepository.deleteById(idFamilySituation);
            return ResponseHandler.generateOkResponse("Family situation " + idFamilySituation + " has properly been deleted !"
            , null);

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND BY ID
    @Override
    public ResponseEntity<Object> findFamilySituationById(Long idFamilySituation) {

        // GET
        Optional<FamilySituation> situation = familySituationRepository.findById(idFamilySituation);

        try {

            return situation.map(s -> ResponseHandler.generateOkResponse("Family situation " + idFamilySituation, s))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Family situation not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findFamilySituationByLabel(String label) {

        try {
            List<FamilySituation> familySituations = familySituationRepository.findFamilySituationByLabel(label);
            if (familySituations.isEmpty()) {
                return ResponseHandler.generateResponse("FamilySituations list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("FamilySituations list", HttpStatus.OK, familySituations);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL
    @Override
    public Long countAll() {
        return familySituationRepository.count();
    }
}
