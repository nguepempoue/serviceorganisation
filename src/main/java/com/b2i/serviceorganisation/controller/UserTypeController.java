package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.UserTypeRequest;
import com.b2i.serviceorganisation.service.UserTypeServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-types")
public class UserTypeController {

    private final UserTypeServiceImplementation userTypeServiceImplementation;

    public UserTypeController(UserTypeServiceImplementation userTypeServiceImplementation) {
        this.userTypeServiceImplementation = userTypeServiceImplementation;
    }

    // CREATE USER TYPE
    @PostMapping
    ResponseEntity<Object> createUserType(@RequestBody UserTypeRequest userTypeRequest){
        return this.userTypeServiceImplementation.createType(userTypeRequest);
    }

    // UPDATE USER TYPE
    @PutMapping("/{idType}")
    ResponseEntity<Object> updateUserType(@RequestBody UserTypeRequest userTypeRequest, @PathVariable Long idType){
        return this.userTypeServiceImplementation.updateType(userTypeRequest, idType);
    }

    // DELETE TYPE BY ID
    @DeleteMapping("/{idType}")
    ResponseEntity<Object> deleteUserType(@PathVariable Long idType){
        return this.userTypeServiceImplementation.deleteType(idType);
    }

    // FIND ALL TYPES
    @GetMapping
    ResponseEntity<Object> getAllUsersType(){
        return this.userTypeServiceImplementation.findAllTypes();
    }

    // FIND CATEGORY BY ID
    @GetMapping("/{idType}")
    public ResponseEntity<Object> findUsersTypeById(@PathVariable("idType") Long idCategory) {
        return userTypeServiceImplementation.findTypeById(idCategory);
    }

    @GetMapping("search")
    public ResponseEntity<Object> findUserTypeByLabel(@RequestParam String label) {
        return userTypeServiceImplementation.findUserTypeByLabel(label);
    }

}
