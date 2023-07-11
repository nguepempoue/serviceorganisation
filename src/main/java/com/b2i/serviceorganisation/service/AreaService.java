package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.AreaRequest;
import org.springframework.http.ResponseEntity;

public interface AreaService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createArea(AreaRequest areaRequest);

    ResponseEntity<Object> findAllAreas();

    ResponseEntity<Object> updateArea(Long id, AreaRequest areaRequest);

    ResponseEntity<Object> deleteById(Long id);

    // MORE //
    ResponseEntity<Object> findAreaById(Long id);

    ResponseEntity<Object> countAllAreas();

    ResponseEntity<Object> addClub(Long idArea, Long idClub);

    ResponseEntity<Object> removeClub(Long idArea, Long idClub);

    ResponseEntity<Object> findAreaByIdClub(Long idClub);

/*    ResponseEntity<Object> addOperatorToPostInArea(Long idOperator, Long idPost, Long idFunction);*/

/*    ResponseEntity<Object> addDataEntryAgent(Long idArea, Long idAgent);

    ResponseEntity<Object> removeDataEntryAgent(Long idArea);

    ResponseEntity<Object> addCommunicationAgent(Long idArea, Long idAgent);

    ResponseEntity<Object> removeCommunicationAgent(Long idArea);*/

    ResponseEntity<Object> getAllAreaUsers(Long idArea);

    ResponseEntity<Object> getCenterOfArea(Long idArea);

    ResponseEntity<Object>findAreasByName(String name);
}
