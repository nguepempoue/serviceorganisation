package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.service.AccountServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImplementation accountServiceImplementation;


    // CREATE ACCOUNT
    @PostMapping("/{idAccountType}")
    public ResponseEntity<Object> createAccount(@PathVariable("idAccountType") Long idAccountType) {
        return accountServiceImplementation.create(idAccountType);
    }


    // FIND ALL ACCOUNTS
    @GetMapping
    public ResponseEntity<Object> findAllAccounts() {
        return accountServiceImplementation.findAllAccounts();
    }


    // DELETE ACCOUNT
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(
            @PathVariable("id") Long id
    ) {
       return accountServiceImplementation.deleteAccountById(id);
    }


    // FIND ACCOUNT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccountById(@PathVariable("id") Long id) {
        return accountServiceImplementation.findAccountById(id);
    }


    // COUNT ALL ACCOUNTS
    @GetMapping("/all")
    public ResponseEntity<Object> countAllAccounts() {
        return accountServiceImplementation.countAllAccounts();
    }


    // SET BALANCE
    @PutMapping("{id}/set-balance")
    public ResponseEntity<Object> setBalance(@PathVariable("id") Long id, @RequestParam("amount") Long amount) {
        return accountServiceImplementation.setBalance(id, amount);
    }
}
