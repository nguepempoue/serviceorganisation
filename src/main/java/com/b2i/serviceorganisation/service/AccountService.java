package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.model.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    // CRUD OPERATIONS //
    Account createAccount(Long idUser, Long idAccountType);

    ResponseEntity<Object> create(Long idAccountType);

    ResponseEntity<Object> findAllAccounts();

    ResponseEntity<Object> deleteAccountById(Long id);

    // MORE
    ResponseEntity<Object> findAccountById(Long id);

    ResponseEntity<Object> countAllAccounts();

    ResponseEntity<Object> setBalance(Long id, Long amount);
}
