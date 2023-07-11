package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.CenterRequest;
import com.b2i.serviceorganisation.service.CenterServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/centers")
public class CenterController {

    private final CenterServiceImplementation centerServiceImplementation;

    public CenterController(CenterServiceImplementation centerServiceImplementation) {
        this.centerServiceImplementation = centerServiceImplementation;
    }

    // FIND ALL CENTERS
    @GetMapping
    public ResponseEntity<Object> findAllCenters() {
        return centerServiceImplementation.findAllCenters();
    }

    // FIND CENTERS BY
    @GetMapping("search")
    public ResponseEntity<Object> findCentersByName(@RequestParam String name) {
        return centerServiceImplementation.findCentersByName(name);
    }

    // CREATE CENTER
    @PostMapping
    public ResponseEntity<Object> createCenter(@RequestBody CenterRequest centerRequest) {
        return centerServiceImplementation.createCenter(centerRequest);
    }


    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCenterById(@PathVariable("id") Long id) {
        return centerServiceImplementation.deleteCenter(id);
    }


    // UPDATE CENTER BY ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCenterById(@PathVariable("id") Long id, @RequestBody CenterRequest centerRequest) {
        return centerServiceImplementation.updateCenter(id, centerRequest);
    }

    // FIND BY IDAREA
    @GetMapping("/findBy/{idArea}")
    public ResponseEntity<Object> findCenterByIdArea(@PathVariable Long idArea) {
        return centerServiceImplementation.findCenterByIdArea(idArea);
    }


    // GET CENTER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCenterById(@PathVariable("id") Long id) {
        return centerServiceImplementation.findById(id);
    }

    //ADD A OPERATOR IN A POST
/*
    @PatchMapping("add-operator")
    public ResponseEntity<Object> addOperatorToPostInMainOffice(@RequestParam("idOperator") Long idOperator,@RequestParam("idPost") Long idPost, @RequestParam("idFunction") Long idFunction){
        return mainOfficeServiceImplementation.addOperatorToPostInMainOffice(idOperator, idPost, idFunction);
    }
*/


/*    // ADD ADMIN SYS
    @PatchMapping("/{idCenter}/add-admin-sys/{idMember}")
    public ResponseEntity<Object> addAdminSys(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.addAdminSys(idCenter, idMember);
    }


    // REMOVE ADMIN SYS
    @PatchMapping("/{idCenter}/remove-admin-sys")
    public ResponseEntity<Object> removeAdminSys(@PathVariable("idCenter") Long idCenter) {
        return centerServiceImplementation.removeAdminSys(idCenter);
    }


    // ADD ACCOUNTANT
    @PatchMapping("/{idCenter}/add-accountant/{idMember}")
    public ResponseEntity<Object> addAccountant(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.addAccountant(idCenter, idMember);
    }

    // REMOVE ADMIN SYS
    @PatchMapping("/{idCenter}/remove-accountant")
    public ResponseEntity<Object> removeAccountant(@PathVariable("idCenter") Long idCenter) {
        return centerServiceImplementation.removeAccountant(idCenter);
    }


    // ADD ACCOUNTANT
    @PatchMapping("/{idCenter}/add-production-manager/{idMember}")
    public ResponseEntity<Object> addProductionManager(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.addProductionManager(idCenter, idMember);
    }


    // REMOVE ADMIN SYS
    @PatchMapping("/{idCenter}/remove-production-manager")
    public ResponseEntity<Object> removeProductionManager(@PathVariable("idCenter") Long idCenter) {
        return centerServiceImplementation.removeProductionManager(idCenter);
    }


    // ADD TO MEMBERS GENERAL ASSEMBLY
    @PatchMapping("/{idCenter}/add-member-to-members-general-assembly/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToMembersGeneralAssembly(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost) {
        return centerServiceImplementation.addMemberToMembersGeneralAssembly(idCenter, idMember, idPost);
    }


    // REMOVE FROM MEMBERS GENERAL ASSEMBLY
    @PatchMapping("/{idCenter}/remove-member-from-members-general-assembly/{idMember}")
    public ResponseEntity<Object> removeFromMembersGeneralAssembly(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.removeMemberFromMembersGeneralAssembly(idCenter, idMember);
    }


    // ADD TO CLUBS GENERAL ASSEMBLY
    @PatchMapping("/{idCenter}/add-member-to-clubs-general-assembly/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToClubsGeneralAssembly(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost) {
        return centerServiceImplementation.addMemberToClubsGeneralAssembly(idCenter, idMember, idPost);
    }


    // REMOVE FROM CLUBS GENERAL ASSEMBLY
    @PatchMapping("/{idCenter}/remove-member-from-clubs-general-assembly/{idMember}")
    public ResponseEntity<Object> removeFromClubsGeneralAssembly(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.removeMemberFromClubsGeneralAssembly(idCenter, idMember);
    }*/


    // ADD AREA
    @PatchMapping("/{idCenter}/add-area/{idArea}")
    public ResponseEntity<Object> addArea(@PathVariable("idCenter") Long idCenter, @PathVariable("idArea") Long idArea) {
        return centerServiceImplementation.addArea(idCenter, idArea);
    }


    // REMOVE AREA
    @PatchMapping("/{idCenter}/remove-area/{idArea}")
    public ResponseEntity<Object> removeArea(@PathVariable("idCenter") Long idCenter, @PathVariable("idArea") Long idArea) {
        return centerServiceImplementation.removeArea(idCenter, idArea);
    }


/*    // ADD TO EXECUTIVE BOARD
    @PatchMapping("/{idCenter}/add-member-to-executive-board/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToExecutiveBoard(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost) {
        return centerServiceImplementation.addMemberToExecutiveBoard(idCenter, idMember, idPost);
    }


    // REMOVE FROM EXECUTIVE BOARD
    @PatchMapping("/{idCenter}/remove-member-from-executive-board/{idMember}")
    public ResponseEntity<Object> removeFromExecutiveBoard(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.removeMemberFromExecutiveBoard(idCenter, idMember);
    }


    // ADD TO DEVELOPMENT COMMITTEE
    @PatchMapping("/{idCenter}/add-member-to-development-committee/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToDevelopmentCommittee(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost) {
        return centerServiceImplementation.addMemberToDevelopmentCommittee(idCenter, idMember, idPost);
    }


    // REMOVE FROM EXECUTIVE BOARD
    @PatchMapping("/{idCenter}/remove-member-from-development-committee/{idMember}")
    public ResponseEntity<Object> removeFromDevelopmentCommittee(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.removeMemberFromDevelopmentCommittee(idCenter, idMember);
    }


    // ADD TO GCC
    @PatchMapping("/{idCenter}/add-member-to-gcc/post/{idPost}/user/{idMember}")
    public ResponseEntity<Object> addToGcc(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember, @PathVariable("idPost") Long idPost) {
        return centerServiceImplementation.addMemberToGovernanceAndCompensationCommittee(idCenter, idMember, idPost);
    }


    // REMOVE FROM GCC
    @PatchMapping("/{idCenter}/remove-member-from-gcc/{idMember}")
    public ResponseEntity<Object> removeFromGcc(@PathVariable("idCenter") Long idCenter, @PathVariable("idMember") Long idMember) {
        return centerServiceImplementation.removeMemberFromGovernanceAndCompensationCommittee(idCenter, idMember);
    }*/


    // GET ALL CENTER USERS
    @GetMapping("/{idCenter}/users")
    public ResponseEntity<Object> getAllCenterUsersId(@PathVariable("idCenter") Long idCenter) {
        return centerServiceImplementation.getAllCenterUsers(idCenter);
    }

    // GET ALL CENTER USERS
    @GetMapping("/user/{idUser}")
    public ResponseEntity<Object> getCenterByUserId(@PathVariable("idUser") Long idUser) {
        return centerServiceImplementation.findCenterByIdUser(idUser);
    }

    // GET ALL USERS OF A CENTER BY ID CENTER
    @GetMapping("/users/{idCenter}")
    public ResponseEntity<Object> findUsersByIdCenter(@PathVariable("idCenter") Long idCenter) {
        return centerServiceImplementation.findUsersByIdCenter(idCenter);
    }

    // GET ALL MUTUALISTS OF A CENTER BY ID CENTER
    @GetMapping("/mutualists/{idCenter}")
    public ResponseEntity<Object> findMutualistsByIdCenter(@PathVariable("idCenter") Long idCenter) {
        return centerServiceImplementation.findMutualistsByIdCenter(idCenter);
    }
}
