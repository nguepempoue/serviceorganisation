package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.ClubRequest;
import com.b2i.serviceorganisation.model.Area;
import org.springframework.http.ResponseEntity;

public interface ClubService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createClub(ClubRequest clubRequest);

    ResponseEntity<Object> findAllClubs();

    ResponseEntity<Object> updateClub(Long id, ClubRequest clubRequest);

    ResponseEntity<Object> deleteClubById(Long id);

    // MORE //
    ResponseEntity<Object> findClubById(Long id);

    ResponseEntity<Object> countAllClubs();

/*
    ResponseEntity<Object> addOperatorToPostInClub(Long idOperator, Long idPost, Long idFunction);
*/

  /*  ResponseEntity<Object> addPilot(Long idClub, Long idPilot);*/

    ResponseEntity<Object> addMember(Long idClub, Long idMember);

 /*   ResponseEntity<Object> removeMember(Long idClub, Long idMember);*/

 /*   ResponseEntity<Object> removePilot(Long idClub);*/

    ResponseEntity<Object> getAllClubUsers(Long idClub);

    ResponseEntity<Object> getAreaOfClub(Long idClub);

    ResponseEntity<Object> changeClubStatus(Long idClub, Long idStatus);

    ResponseEntity<Object>findClubsByName(String name);

    ResponseEntity<Object> transferClubToAnotherArea(Long idArea, Long idClub);
}
