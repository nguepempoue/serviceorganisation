package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.ClubRequest;
import com.b2i.serviceorganisation.service.ClubServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubServiceImplementation clubServiceImplementation;

    public ClubController(ClubServiceImplementation clubServiceImplementation) {
        this.clubServiceImplementation = clubServiceImplementation;
    }


    // CREATE CLUB
    @PostMapping
    public ResponseEntity<Object> createClub(@RequestBody ClubRequest clubRequest) {
        return clubServiceImplementation.createClub(clubRequest);
    }


/*    // ADD PILOT TO CLUB
    @PatchMapping("/{idClub}/add-pilot/{idPilot}")
    public ResponseEntity<Object> addPilotToClub(@PathVariable ("idClub") Long idClub, @PathVariable ("idPilot") Long idPilot) {
        return clubServiceImplementation.addPilot(idClub,idPilot);
    }*/


    // FIND ALL CLUBS
    @GetMapping
    public ResponseEntity<Object> findAllClubs() {
        return clubServiceImplementation.findAllClubs();
    }

    // FIND CLUBS BY NAME
    @GetMapping("search")
    public ResponseEntity<Object> findClubsByName(@RequestParam String name) {
        return clubServiceImplementation.findClubsByName(name);
    }

    // FIND CLUB BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> findClubById(@PathVariable("id") Long id) {
        return clubServiceImplementation.findClubById(id);
    }


    // COUNT ALL CLUBS
    @GetMapping("/all")
    public ResponseEntity<Object> countAllClubs() { return clubServiceImplementation.countAllClubs(); }


    // DELETE CLUB
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        return clubServiceImplementation.deleteClubById(id);
    }


    // UPDATE CLUB
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClub(@PathVariable("id") Long id, @RequestBody ClubRequest clubRequest) {
        return clubServiceImplementation.updateClub(id, clubRequest);
    }


    // ADD MEMBER
    @PatchMapping("/{idClub}/add-member/{idMember}")
    public ResponseEntity<Object> addMember(@PathVariable("idClub") Long idClub, @PathVariable("idMember") Long idMember) {
        return clubServiceImplementation.addMember(idClub, idMember);
    }


/*
    // REMOVE MEMBER
    @PatchMapping("/{idClub}/remove-member/{idMember}")
    public ResponseEntity<Object> removeMember(@PathVariable("idClub") Long idClub, @PathVariable("idMember") Long idMember) {
        return clubServiceImplementation.removeMember(idClub, idMember);
    }


    // REMOVE PILOT
    @PatchMapping("/{idClub}/remove-pilot")
    public ResponseEntity<Object> removePilot(@PathVariable("idClub") Long idClub) {
        return clubServiceImplementation.removePilot(idClub);
    }
*/


    // GET CLUB USERS
    @GetMapping("/{idClub}/users")
    public ResponseEntity<Object> getAllClubUsersId(@PathVariable("idClub") Long idClub) {
        return clubServiceImplementation.getAllClubUsers(idClub);
    }


    // GET AREA OF CLUB
    @GetMapping("/{idClub}/area")
    public ResponseEntity<Object> getAreaOfClub(@PathVariable("idClub") Long idClub) {
        return clubServiceImplementation.getAreaOfClub(idClub);
    }


    // CHANGE CLUB STATUS
    @PatchMapping("/{idClub}/status/{idStatus}")
    public ResponseEntity<Object> changeClubStatus(@PathVariable("idClub") Long idClub, @PathVariable("idStatus") Long idStatus) {
        return clubServiceImplementation.changeClubStatus(idClub, idStatus);
    }

    // ADD CLUB TO AREA
    @PatchMapping("/{idArea}/transfer-club/{idClub}")
    public ResponseEntity<Object> transferClubToAnotherArea(@PathVariable Long idArea, @PathVariable Long idClub) {
        return clubServiceImplementation.transferClubToAnotherArea(idArea, idClub);
    }
}
