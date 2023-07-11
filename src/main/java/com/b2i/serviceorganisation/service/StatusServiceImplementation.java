package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.StatusRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.Status;
import com.b2i.serviceorganisation.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImplementation implements StatusService {

    @Autowired
    private StatusRepository statusRepository;


    // CREATE STATUS
    @Override
    public ResponseEntity<Object> createStatus(StatusRequest statusRequest) {

        // NEW STATUS
        Status status = new Status();

        try {

            // CHECK STATUS LABEL
            if(statusRequest.getLabel() == null) {
                throw new Exception("Status label can't be null !");
            }

            status.setLabel(statusRequest.getLabel());
            return ResponseHandler.generateResponse("Status label created",
                    HttpStatus.CREATED, statusRepository.save(status));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL STATUS
    @Override
    public ResponseEntity<Object> findAllStatus() {

        // GET ALL STATUS
        List<Status> statuses = statusRepository.findAll();

        try {

            if(statuses.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Status list is empty !");
            }
            return ResponseHandler.generateResponse("Status list", HttpStatus.OK, statuses);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE STATUS
    @Override
    public ResponseEntity<Object> updateStatus(Long idStatus, StatusRequest statusRequest) {

        // GET STATUS
        Optional<Status> status = statusRepository.findById(idStatus);

        try {

            return status.map((s) -> {

                if(statusRequest.getLabel() != null && !statusRequest.getLabel().equals("")) {
                    s.setLabel(statusRequest.getLabel());
                }
                return ResponseHandler.generateResponse("Status updated !", HttpStatus.OK, statusRepository.save(s));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Status not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE STATUS
    @Override
    public ResponseEntity<Object> deleteStatus(Long idStatus) {

        // GET STATUS
        Optional<Status> status = statusRepository.findById(idStatus);

        try {

            if(!status.isPresent())
            {
                return ResponseHandler.generateNotFoundResponse("Status not found !");
            }

            statusRepository.deleteById(idStatus);
            return ResponseHandler.generateResponse("Status deleted !", HttpStatus.OK, null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND STATUS BY ID
    @Override
    public ResponseEntity<Object> findStatusById(Long idStatus) {

        // GET STATUS
        Optional<Status> status = statusRepository.findById(idStatus);

        try {
            return status.map(s -> ResponseHandler.generateResponse("Status " + idStatus, HttpStatus.OK, s))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Status not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL STATUS
    @Override
    public Long countAllStatus() {
        return statusRepository.count();
    }
}
