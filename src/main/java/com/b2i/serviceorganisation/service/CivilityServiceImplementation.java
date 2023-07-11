package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.dto.request.CivilityRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.Civility;
import com.b2i.serviceorganisation.repository.CivilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CivilityServiceImplementation implements CivilityService {

    @Autowired
    private CivilityRepository civilityRepository;


    // CREATE CIVILITY
    @Override
    public ResponseEntity<Object> createCivility(CivilityRequest civilityRequest) {

        try {

            // CHECK NAME
            Utils.checkStringValues(civilityRequest.getName(), "Civility name");

            // CHECK LABEL
            Utils.checkStringValues(civilityRequest.getLabel(), "Civility label");

            // SETTING VALUES
            Civility civility = new Civility(civilityRequest.getName(), civilityRequest.getLabel());
            return ResponseHandler.generateCreatedResponse("Civility created !", civilityRepository.save(civility));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL CIVILITIES
    @Override
    public ResponseEntity<Object> findAllCivilities() {

        List<Civility> civilities = civilityRepository.findAll();

        try {
            if(civilities.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }
            return ResponseHandler.generateOkResponse("Civilities list", civilities);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE CIVILITY
    @Override
    public ResponseEntity<Object> updateCivility(Long idCivility, CivilityRequest civilityRequest) {

        // GET CIVILITY
        Optional<Civility> civility = civilityRepository.findById(idCivility);

        try {

            return civility.map(c -> {

                if(civilityRequest.getName() != null) {
                    c.setName(civilityRequest.getName());
                }

                try {
                    Utils.checkStringValues(civilityRequest.getLabel(), "Civility label");
                    c.setLabel(civilityRequest.getLabel());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                return ResponseHandler.generateOkResponse("Civility " + idCivility + " has properly been updated !",
                        civilityRepository.save(c));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Civility not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE CIVILITY
    @Override
    public ResponseEntity<Object> deleteCivility(Long idCivility) {

        // GET CIVILITY
        Optional<Civility> civility = civilityRepository.findById(idCivility);

        try {
            if(!civility.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Civility not found !");
            }
            civilityRepository.deleteById(idCivility);
            return ResponseHandler.generateOkResponse("Civility " + idCivility + " has properly been deleted !", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND CIVILITY
    @Override
    public ResponseEntity<Object> findCivilityById(Long idCivility) {

        // GET CIVILITY
        Optional<Civility> civility = civilityRepository.findById(idCivility);

        try {

            return civility.map(c -> ResponseHandler.generateOkResponse("Civility " + idCivility, c))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Civility not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findCivilityByName(String name) {

        try {
            List<Civility> civilities = civilityRepository.findCivilityByName(name);
            if (civilities.isEmpty()) {
                return ResponseHandler.generateResponse("Civilities list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("Civilities list", HttpStatus.OK, civilities);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL
    @Override
    public Long countAll() {
        return civilityRepository.count();
    }
}
