package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.AreaRequest;
import com.b2i.serviceorganisation.service.AreaServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaServiceImplementation areaServiceImplementation;

    public AreaController(AreaServiceImplementation areaServiceImplementation) {
        this.areaServiceImplementation = areaServiceImplementation;
    }


    // FIND ALL AREAS
    @GetMapping
    public ResponseEntity<Object> findAllAreas() {
        return areaServiceImplementation.findAllAreas();
    }

    // FIND AREAS BY NAME
    @GetMapping("search")
    public ResponseEntity<Object> findAreasByName(@RequestParam String name) {
        return areaServiceImplementation.findAreasByName(name);
    }

    // CREATE AREA
    @PostMapping
    public ResponseEntity<Object> createArea(@RequestBody AreaRequest areaRequest) {
        return areaServiceImplementation.createArea(areaRequest);
    }


    // COUNT ALL AREAS
    @GetMapping("/all")
    public ResponseEntity<Object> countAllAreas() {
        return areaServiceImplementation.countAllAreas();
    }


    // FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> findAreaById(@PathVariable Long id) {
        return areaServiceImplementation.findAreaById(id);
    }

    // FIND BY IDCLUB
    @GetMapping("/findBy/{idClub}")
    public ResponseEntity<Object> findAreaByIdClub(@PathVariable Long idClub) {
        return areaServiceImplementation.findAreaByIdClub(idClub);
    }


    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAreaById(@PathVariable Long id) {
        return areaServiceImplementation.deleteById(id);
    }


    // UPDATE AREA
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArea(@PathVariable Long id, @RequestBody AreaRequest areaRequest) {
        return areaServiceImplementation.updateArea(id, areaRequest);
    }


    // ADD CLUB TO AREA
    @PatchMapping("/{idArea}/add-club/{idClub}")
    public ResponseEntity<Object> addClubToArea(@PathVariable Long idArea, @PathVariable Long idClub) {
        return areaServiceImplementation.addClub(idArea, idClub);
    }


    // REMOVE CLUB FROM AREA
    @PatchMapping("/{idArea}/remove-club/{idClub}")
    public ResponseEntity<Object> removeClubFromArea(@PathVariable Long idArea, @PathVariable Long idClub) {
        return areaServiceImplementation.removeClub(idArea, idClub);
    }


/*    // ADD DATA ENTRY AGENT
    @PatchMapping("/{idArea}/add-data-entry-agent/{idAgent}")
    public ResponseEntity<Object> addDataEntryAgentToArea(@PathVariable Long idArea, @PathVariable Long idAgent) {
        return areaServiceImplementation.addDataEntryAgent(idArea, idAgent);
    }


    // ADD COMMUNICATION AGENT
    @PatchMapping("/{idArea}/add-communication-agent/{idAgent}")
    public ResponseEntity<Object> addCommunicationAgentToArea(@PathVariable Long idArea, @PathVariable Long idAgent) {
        return areaServiceImplementation.addCommunicationAgent(idArea, idAgent);
    }


    // REMOVE DATA ENTRY AGENT
    @PatchMapping("/{idArea}/remove-data-entry-agent")
    public ResponseEntity<Object> removeDataEntryAgentFromArea(@PathVariable Long idArea) {
        return areaServiceImplementation.removeDataEntryAgent(idArea);
    }


    // REMOVE COMMUNICATION AGENT
    @PatchMapping("/{idArea}/remove-communication-agent")
    public ResponseEntity<Object> removeCommunicationAgentFromArea(@PathVariable Long idArea) {
        return areaServiceImplementation.removeCommunicationAgent(idArea);
    }*/


    // GET ALL USERS OF AREA
    @GetMapping("/{idArea}/users")
    public ResponseEntity<Object> getAllAreaUsersId(@PathVariable("idArea") Long idArea) {
        return areaServiceImplementation.getAllAreaUsers(idArea);
    }


    // GET CENTER OF AREA
    @GetMapping("/{idArea}/center")
    public ResponseEntity<Object> getCenterOfArea(@PathVariable("idArea") Long idArea) {
        return areaServiceImplementation.getCenterOfArea(idArea);
    }
}
