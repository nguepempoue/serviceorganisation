package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.BeneficiaryRequest;
import com.b2i.serviceorganisation.dto.request.UserRequest;
import com.b2i.serviceorganisation.model.User;
import org.springframework.http.ResponseEntity;


public interface UserService {

    // CRUD OPERATIONS //
    ResponseEntity<Object> createUserMember(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry);

    ResponseEntity<Object> createUserMutualist(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry, Long idType, Long idCategory);

    ResponseEntity<Object> createUserAdmin(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry);

    ResponseEntity<Object> createUserOperator(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry);

    ResponseEntity<Object> findAllUsers();

    ResponseEntity<Object> updateUser(Long id, UserRequest userRequest);

    ResponseEntity<Object> deleteUserById(Long id);

    ResponseEntity<Object> addRoleToUser(Long idUser, Long idRole);

    ResponseEntity<Object>removeRoleFromUser(Long idUser, Long idRole);

    // MORE //
    ResponseEntity<Object> findUserById(Long id);

    ResponseEntity<Object> countAll();

    ResponseEntity<Object> addSponsoredUsers(Long id, Long idToAdd);

    ResponseEntity<Object> findAllUserAccounts(Long id);

    ResponseEntity<Object> findAccountByUserAndAccountType(Long idUser, Long idAccountType);

    ResponseEntity<Object> findAllAdmins();

    ResponseEntity<Object> findAllMembers();

    ResponseEntity<Object> findAllMutualists();

    ResponseEntity<Object> findUserByEmail(String email);

    ResponseEntity<Object> getSponsorOfUser(Long id);

    ResponseEntity<Object> changeUserStatus(Long idUser, Long idStatus);

    ResponseEntity<Object> addBeneficiary(Long idUser, BeneficiaryRequest beneficiaryRequest, Long idPieceType);

    ResponseEntity<Object> removeBeneficiary(Long idUser, Long idBeneficiary);

    ResponseEntity<Object> findAllBeneficiariesOfUser(Long idUser);

    ResponseEntity<Object> changeCivility(Long idUser, Long idCivility);

    ResponseEntity<Object> changeFamilySituation(Long idUser, Long idFamilySituation);

    ResponseEntity<Object>findUsersByLastName(String lastName);

    ResponseEntity<Object> findUsersByFirstName(String firstName);

    ResponseEntity<Object> findUsersByPhoneNumber(String phoneNumber);

    ResponseEntity<Object> findUsersByRole(String name);

    ResponseEntity<Object> disableUser(Long idUser);

    ResponseEntity<Object> findUsersByIdType(Long idType);

    ResponseEntity<Object> findUsersByIdCategory(Long idCategory);

    ResponseEntity<Object> transferMemberToAnotherClub(Long idClub, Long idMember);

    // SIMPLE FUNCTIONS //
    Long countAllUsers();

    User saveUser(User user);
}
