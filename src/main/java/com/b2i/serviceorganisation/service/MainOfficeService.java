package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.MainOfficeRequest;
import org.springframework.http.ResponseEntity;

public interface MainOfficeService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createMainOffice(MainOfficeRequest mainOfficeRequest);

    ResponseEntity<Object> findAllMainOffices();

    ResponseEntity<Object> updateMainOfficeRequest(Long id, MainOfficeRequest mainOfficeRequest);

    ResponseEntity<Object> deleteMainOffice(Long id);

    // MORE //
    ResponseEntity<Object> findMainOfficeById(Long id);

    ResponseEntity<Object> addCenter(Long idMainOffice, Long idCenter);

    ResponseEntity<Object> removeCenter(Long idMainOffice, Long idCenter);

/*    ResponseEntity<Object> addToCentersGeneralAssembly(Long idMainOffice, Long idMember, Long idPost);

    ResponseEntity<Object> removeFromCentersGeneralAssembly(Long idMainOffice, Long idMember);

    ResponseEntity<Object> addToExecutiveBoard(Long idMainOffice, Long idMember, Long idPost);

    ResponseEntity<Object> removeFromExecutiveBoard(Long idMainOffice, Long idMember);

    ResponseEntity<Object> addToGovernanceAndCompensationCommittee(Long idMainOffice, Long idMember, Long idPost);

    ResponseEntity<Object> removeFromGovernanceAndCompensationCommittee(Long idMainOffice, Long idMember);

    ResponseEntity<Object> addToStrategicDevelopmentCommittee(Long idMainOffice, Long idMember, Long idPost);

    ResponseEntity<Object> removeFromStrategicDevelopmentCommittee(Long idMainOffice, Long idMember);

    ResponseEntity<Object> addToProductionAndMonitoringCommittee(Long idMainOffice, Long idMember, Long idPost);

    ResponseEntity<Object> removeFromProductionAndMonitoringCommittee(Long idMainOffice, Long idMember);*/
}
