package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.MainOfficeRequest;
import com.b2i.serviceorganisation.service.MainOfficeServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main-office")
@CrossOrigin("*")
public class MainOfficeController {

    private final MainOfficeServiceImplementation mainOfficeServiceImplementation;

    public MainOfficeController(MainOfficeServiceImplementation mainOfficeServiceImplementation) {
        this.mainOfficeServiceImplementation = mainOfficeServiceImplementation;
    }


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllOffices() {
        return mainOfficeServiceImplementation.findAllMainOffices();
    }


    // CREATE MAIN OFFICE
    @PostMapping
    public ResponseEntity<Object> createMainOffice(@RequestBody MainOfficeRequest mainOfficeRequest) {
        return mainOfficeServiceImplementation.createMainOffice(mainOfficeRequest);
    }


    // DELETE MAIN OFFICE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMainOffice(@PathVariable("id") Long id) {
        return mainOfficeServiceImplementation.deleteMainOffice(id);
    }


    // UPDATE OFFICE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOffice(
            @PathVariable("id") Long id,
            @RequestBody MainOfficeRequest mainOfficeRequest) {
        return mainOfficeServiceImplementation.updateMainOfficeRequest(id, mainOfficeRequest);
    }


    // FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return mainOfficeServiceImplementation.findMainOfficeById(id);
    }


    // ADD CENTER
    @PatchMapping("/{idMainOffice}/add-center/{idCenter}")
    public ResponseEntity<Object> addCenter(
            @PathVariable("idMainOffice") Long idMainOffice,
            @PathVariable("idCenter") Long idCenter
    ){
        return mainOfficeServiceImplementation.addCenter(idMainOffice, idCenter);
    }

    // REMOVE CENTER
    @PatchMapping("/{idMainOffice}/remove-center/{idCenter}")
    public ResponseEntity<Object> removeCenter(@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idCenter") Long idCenter){
        return mainOfficeServiceImplementation.removeCenter(idMainOffice, idCenter);
    }



/*    // ADD TO CENTERS GENERAL ASSEMBLY
    @PatchMapping("/{idMainOffice}/add-to-centers-general-assembly/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToCGA (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost){
        return mainOfficeServiceImplementation.addToCentersGeneralAssembly(idMainOffice, idMember, idPost);
    }


    // REMOVE FROM CENTERS GENERAL ASSEMBLY
    @PatchMapping("/{idMainOffice}/remove-from-centers-general-assembly/{idMember}")
    public ResponseEntity<Object> removeFromCGA (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember){
        return mainOfficeServiceImplementation.removeFromCentersGeneralAssembly(idMainOffice, idMember);
    }


    // ADD TO EXECUTIVE BOARD
    @PatchMapping("/{idMainOffice}/add-to-executive-board/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToExecutiveBoard (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost){
        return mainOfficeServiceImplementation.addToExecutiveBoard(idMainOffice, idMember, idPost);
    }


    // REMOVE FROM EXECUTIVE BOARD
    @PatchMapping("/{idMainOffice}/remove-from-executive-board/{idMember}")
    public ResponseEntity<Object> removeFromExecutiveBoard (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember){
        return mainOfficeServiceImplementation.removeFromExecutiveBoard(idMainOffice, idMember);
    }


    // ADD TO GCC
    @PatchMapping("/{idMainOffice}/add-to-governance-and-compensation-committee/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToGcc (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost){
        return mainOfficeServiceImplementation.addToGovernanceAndCompensationCommittee(idMainOffice, idMember, idPost);
    }


    // REMOVE FROM GCC
    @PatchMapping("/{idMainOffice}/remove-from-governance-and-compensation-committee/{idMember}")
    public ResponseEntity<Object> removeFromGcc (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember){
        return mainOfficeServiceImplementation.removeFromGovernanceAndCompensationCommittee(idMainOffice, idMember);
    }


    // ADD TO SDC
    @PatchMapping("/{idMainOffice}/add-to-strategic-development-committee/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToSdc (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost){
        return mainOfficeServiceImplementation.addToStrategicDevelopmentCommittee(idMainOffice, idMember, idPost);
    }


    // REMOVE FROM SDC
    @PatchMapping("/{idMainOffice}/remove-from-strategic-development-committee/{idMember}")
    public ResponseEntity<Object> removeFromSdc (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember){
        return mainOfficeServiceImplementation.removeFromStrategicDevelopmentCommittee(idMainOffice, idMember);
    }


    // ADD TO PMC
    @PatchMapping("/{idMainOffice}/add-to-production-and-monitoring-committee/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToPmc (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost){
        return mainOfficeServiceImplementation.addToProductionAndMonitoringCommittee(idMainOffice, idMember, idPost);
    }


    // REMOVE FROM SDC
    @PatchMapping("/{idMainOffice}/remove-from-production-and-monitoring-committee/{idMember}")
    public ResponseEntity<Object> removeFromPmc (@PathVariable("idMainOffice") Long idMainOffice, @PathVariable("idMember") Long idMember){
        return mainOfficeServiceImplementation.removeFromProductionAndMonitoringCommittee(idMainOffice, idMember);
    }*/
}
