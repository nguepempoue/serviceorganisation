package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.BeneficiaryRequest;
import com.b2i.serviceorganisation.dto.request.UserRequest;
import com.b2i.serviceorganisation.service.UserServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImplementation userServiceimplementation;

    public UserController(UserServiceImplementation userServiceimplementation) {
        this.userServiceimplementation = userServiceimplementation;
    }


    // CREATE MEMBER
    @PostMapping("/member/sponsor/{idSponsor}/civility/{idCivility}/pieceType/{idPieceType}/situation/{idFamilySituation}/country/{idCountry}")
    public ResponseEntity<Object> createMember (@RequestBody UserRequest userRequest, @PathVariable("idSponsor") Long idSponsor, @PathVariable("idCivility") Long idCivility, @PathVariable("idPieceType") Long idPieceType, @PathVariable("idFamilySituation") Long idFamilySituation, @PathVariable("idCountry") Long idCountry) {
        return userServiceimplementation.createUserMember(userRequest, idSponsor, idCivility, idPieceType, idFamilySituation, idCountry);
    }


    // CREATE MUTUALIST
    @PostMapping("/mutualist/sponsor/{idSponsor}/civility/{idCivility}/pieceType/{idPieceType}/situation/{idFamilySituation}/country/{idCountry}/type/{idType}/category/{idCategory}")
    public ResponseEntity<Object> createMutualist (@RequestBody UserRequest userRequest, @PathVariable("idSponsor") Long idSponsor, @PathVariable("idCivility") Long idCivility, @PathVariable("idPieceType") Long idPieceType, @PathVariable("idFamilySituation") Long idFamilySituation, @PathVariable("idCountry") Long idCountry, @PathVariable("idType") Long idType, @PathVariable("idCategory") Long idCategory) {
        return userServiceimplementation.createUserMutualist(userRequest, idSponsor, idCivility, idPieceType, idFamilySituation, idCountry, idType, idCategory);
    }


    // CREATE ADMIN
    @PostMapping("/admin/sponsor/{idSponsor}/civility/{idCivility}/pieceType/{idPieceType}/situation/{idFamilySituation}/country/{idCountry}")
    public ResponseEntity<Object> createAdmin (@RequestBody UserRequest userRequest, @PathVariable("idSponsor") Long idSponsor, @PathVariable("idCivility") Long idCivility, @PathVariable("idPieceType") Long idPieceType, @PathVariable("idFamilySituation") Long idFamilySituation, @PathVariable("idCountry") Long idCountry) {
        return userServiceimplementation.createUserAdmin(userRequest,idSponsor, idCivility, idPieceType, idFamilySituation, idCountry);
    }


    // CREATE OPERATOR
    @PostMapping("/operator/sponsor/{idSponsor}/civility/{idCivility}/pieceType/{idPieceType}/situation/{idFamilySituation}/country/{idCountry}")
    public ResponseEntity<Object> createOperator (@RequestBody UserRequest userRequest, @PathVariable("idSponsor") Long idSponsor, @PathVariable("idCivility") Long idCivility, @PathVariable("idPieceType") Long idPieceType, @PathVariable("idFamilySituation") Long idFamilySituation, @PathVariable("idCountry") Long idCountry) {
        return userServiceimplementation.createUserOperator(userRequest, idSponsor, idCivility, idPieceType, idFamilySituation, idCountry);
    }


    // READ
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return userServiceimplementation.findAllUsers();
    }

    @GetMapping("search")
    public ResponseEntity<Object> findUsersByLastNameOrFirstNameOrPhoneNumber(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String phoneNumber) {
       if(lastName!= null && !lastName.equals("")){
           return userServiceimplementation.findUsersByLastName(lastName);
       }else if(firstName!= null && !firstName.equals("")){
           return userServiceimplementation.findUsersByFirstName(firstName);
        }else if(phoneNumber!= null && !phoneNumber.equals("")){
           return userServiceimplementation.findUsersByPhoneNumber(phoneNumber);
       }
       return null;
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMemberById(@PathVariable ("id") Long id, @RequestBody UserRequest userRequest) {
        return userServiceimplementation.updateUser(id, userRequest);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        return userServiceimplementation.deleteUserById(id);
    }


    // FIND MEMBER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable("id") Long id) {
        return userServiceimplementation.findUserById(id);
    }


    // COUNT ALL MEMBERS
    @GetMapping("/all")
    public ResponseEntity<Object> countAllUsers() { return userServiceimplementation.countAll(); }



    // ADD SPONSORED MEMBER TO ANOTHER
    @PatchMapping("/{id}/add-sponsored-member/{idToAdd}")
    public ResponseEntity<Object> addSponsoredMember(@PathVariable("id") Long id, @PathVariable("idToAdd") Long idToAdd) {
        return userServiceimplementation.addSponsoredUsers(id, idToAdd);
    }


    // FIND ALL USER ACCOUNTS
    @GetMapping("/{id}/accounts")
    public ResponseEntity<Object> getAllUserAccounts(@PathVariable ("id") Long idUser) {
        return userServiceimplementation.findAllUserAccounts(idUser);
    }


    // FIND ALL ADMINS
    @GetMapping("/admin")
    public ResponseEntity<Object> getAllAdmins() {
        return userServiceimplementation.findAllAdmins();
    }


    // FIND ALL ADMINS
    @GetMapping("/member")
    public ResponseEntity<Object> getAllMembers() {
        return userServiceimplementation.findAllMembers();
    }


    // FIND ALL ADMINS
    @GetMapping("/mutualist")
    public ResponseEntity<Object> getAllMutualists() {
        return userServiceimplementation.findAllMutualists();
    }


    // GET USER BY EMAIL
    @GetMapping("/by-email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable ("email") String email) {
        return userServiceimplementation.findUserByEmail(email);
    }


    // GET SPONSOR OF USER
    @GetMapping("/{id}/sponsor")
    public ResponseEntity<Object> getSponsor(@PathVariable("id") Long id) {
        return userServiceimplementation.getSponsorOfUser(id);
    }


    // CHANGE USER STATUS
    @PatchMapping("/{idUser}/status/{idStatus}")
    public ResponseEntity<Object> changeUserStatus(@PathVariable("idUser") Long idUser, @PathVariable("idStatus") Long idStatus) {
        return userServiceimplementation.changeUserStatus(idUser, idStatus);
    }


    // ADD BENEFICIARY
    @PatchMapping("/{idUser}/add-beneficiary/pieceType/{idPieceType}")
    public ResponseEntity<Object> addBeneficiary(@PathVariable("idUser") Long idUser, @RequestBody BeneficiaryRequest beneficiaryRequest, @PathVariable("idPieceType") Long idPieceType) {
        return userServiceimplementation.addBeneficiary(idUser, beneficiaryRequest, idPieceType);
    }


    // REMOVE BENEFICIARY OF USER
    @PatchMapping("/{idUser}/remove-beneficiary/{idBeneficiary}")
    public ResponseEntity<Object> removeBeneficiary(@PathVariable("idUser") Long idUser,@PathVariable("idBeneficiary") Long idBeneficiary) {
        return userServiceimplementation.removeBeneficiary(idUser, idBeneficiary);
    }


    // GET ALL BENEFICIARIES
    @GetMapping("/{idUser}/all-beneficiaries")
    public ResponseEntity<Object> findAllBeneficiariesOfUser(@PathVariable("idUser") Long idUser) {
        return userServiceimplementation.findAllBeneficiariesOfUser(idUser);
    }


    // CHANGE CIVILITY
    @PatchMapping("/{idUser}/civility/{idCivility}")
    public ResponseEntity<Object> changeCivility(@PathVariable("idUser") Long idUser, @PathVariable("idCivility") Long idCivility) {
        return userServiceimplementation.changeCivility(idUser, idCivility);
    }


    // CHANGE FAMILY SITUATION
    @PatchMapping("/{idUser}/situation/{idFamilySituation}")
    public ResponseEntity<Object> changeFamilySituation(@PathVariable("idUser") Long idUser, @PathVariable("idFamilySituation") Long idFamilySituation) {
        return userServiceimplementation.changeFamilySituation(idUser, idFamilySituation);
    }


    // GET ACCOUNT BY USER AND ACCOUNT TYPE
    @GetMapping("/{idUser}/account/account-type/{idAccountType}")
    public ResponseEntity<Object> findAccountByUserAndAccountType(@PathVariable("idUser") Long idUser, @PathVariable("idAccountType") Long idAccountType) {
        return userServiceimplementation.findAccountByUserAndAccountType(idUser, idAccountType);
    }

    @GetMapping("filter-by-role-name")
    public ResponseEntity<Object> findUsersByRoleName(@RequestParam String name) {
        return userServiceimplementation.findUsersByRole(name);
    }

    // ADD ROLE TO USER
    @PatchMapping("/user-add-role/{idUser}/role/{idRole}")
    public ResponseEntity<Object> addRoleToUser(@PathVariable("idUser") Long idUser, @PathVariable("idRole") Long idRole) {
        return userServiceimplementation.addRoleToUser(idUser, idRole);
    }

    // ADD ROLE TO USER
    @PatchMapping("/user-remove/{idUser}/role/{idRole}")
    public ResponseEntity<Object> removeRoleFromUser(@PathVariable("idUser") Long idUser, @PathVariable("idRole") Long idRole) {
        return userServiceimplementation.removeRoleFromUser(idUser, idRole);
    }

    // DESABLE A USER
    @PatchMapping("/disable/{idUser}")
    public ResponseEntity<Object> disableUser(@PathVariable("idUser") Long idUser) {
        return userServiceimplementation.disableUser(idUser);
    }

    //GET USERS BY ID TYPE
    @GetMapping("user-type/{idType}")
    public ResponseEntity<Object> findUsersByIdType(@PathVariable("idType") Long idType) {
        return userServiceimplementation.findUsersByIdType(idType);
    }

    //GET USERS BY ID CATEGORY
    @GetMapping("user-category/{idCategory}")
    public ResponseEntity<Object> findUsersByIdCategory(@PathVariable("idCategory") Long idCategory) {
        return userServiceimplementation.findUsersByIdCategory(idCategory);
    }

    // ADD MEMBER
    @PatchMapping("/{idClub}/tranfer/{idMember}")
    public ResponseEntity<Object> transferMemberToAnotherClub(@PathVariable("idClub") Long idClub, @PathVariable("idMember") Long idMember) {
        return userServiceimplementation.transferMemberToAnotherClub(idClub, idMember);
    }
}
