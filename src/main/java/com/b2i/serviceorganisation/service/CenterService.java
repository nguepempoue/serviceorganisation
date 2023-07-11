package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.CenterRequest;
import org.springframework.http.ResponseEntity;

public interface CenterService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createCenter(CenterRequest centerRequest);

    ResponseEntity<Object> findAllCenters();

    ResponseEntity<Object> updateCenter(Long id, CenterRequest centerRequest);

    ResponseEntity<Object> deleteCenter(Long id);

    // MORE //
    ResponseEntity<Object> findById(Long id);

    //ADD POST
    ResponseEntity<Object> addPost(Long idCenter, Long idPost);

    ResponseEntity<Object> findCenterByIdArea(Long idArea);

    ResponseEntity<Object> findCenterByIdUser(Long idUser);

    ResponseEntity<Object> findUsersByIdCenter(Long idCenter);

/*
    ResponseEntity<Object> addOperatorToPostInCenter(Long idOperator, Long idPost, Long idFunction);
*/

/*
    ResponseEntity<Object> addAdminSys(Long idCenter, Long idMember);

    ResponseEntity<Object> removeAdminSys(Long idCenter);

    ResponseEntity<Object> addAccountant(Long idCenter, Long idMember);
*/

/*    ResponseEntity<Object> removeAccountant(Long idCenter);

    ResponseEntity<Object> addProductionManager(Long idCenter, Long idMember);

    ResponseEntity<Object> removeProductionManager(Long idCenter);

    ResponseEntity<Object> addMemberToMembersGeneralAssembly(Long idCenter, Long idMember, Long idPost);

    ResponseEntity<Object> removeMemberFromMembersGeneralAssembly(Long idCenter, Long idMember);

    ResponseEntity<Object> addMemberToClubsGeneralAssembly(Long idCenter, Long idMember, Long idPost);

    ResponseEntity<Object> removeMemberFromClubsGeneralAssembly(Long idCenter, Long idMember);*/

    ResponseEntity<Object> addArea(Long idCenter, Long idArea);

    ResponseEntity<Object> removeArea(Long idCenter, Long idArea);

/*    ResponseEntity<Object> addMemberToExecutiveBoard(Long idCenter, Long idMember, Long idPost);

    ResponseEntity<Object> removeMemberFromExecutiveBoard(Long idCenter, Long idMember);

    ResponseEntity<Object> addMemberToDevelopmentCommittee(Long idCenter, Long idMember, Long idPost);

    ResponseEntity<Object> removeMemberFromDevelopmentCommittee(Long idCenter, Long idMember);

    ResponseEntity<Object> addMemberToGovernanceAndCompensationCommittee(Long idCenter, Long idMember, Long idPost);

    ResponseEntity<Object> removeMemberFromGovernanceAndCompensationCommittee(Long idCenter, Long idMember);*/

    ResponseEntity<Object> getAllCenterUsers(Long idCenter);

    ResponseEntity<Object>findCentersByName(String name);

    ResponseEntity<Object>findMutualistsByIdCenter(Long idCenter);
}
