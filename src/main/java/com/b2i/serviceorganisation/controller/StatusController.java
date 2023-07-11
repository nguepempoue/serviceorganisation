package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.StatusRequest;
import com.b2i.serviceorganisation.service.StatusServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
@CrossOrigin("*")
public class StatusController {


    @Autowired
    private StatusServiceImplementation statusServiceImplementation;


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllStatus(){
        return statusServiceImplementation.findAllStatus();
    }


    // UPDATE STATUS
    @PutMapping("/{idStatus}")
    public ResponseEntity<Object> updateStatus(@PathVariable("idStatus") Long idStatus, @RequestBody StatusRequest statusRequest) {
        return statusServiceImplementation.updateStatus(idStatus, statusRequest);
    }


    // DELETE STATUS
    @DeleteMapping("/{idStatus}")
    public ResponseEntity<Object> deleteStatus(@PathVariable("idStatus") Long idStatus) {
        return statusServiceImplementation.deleteStatus(idStatus);
    }


    // FIND STATUS BY ID
    @GetMapping("/{idStatus}")
    public ResponseEntity<Object> findStatusById(@PathVariable("idStatus") Long idStatus) {
        return statusServiceImplementation.findStatusById(idStatus);
    }
}
