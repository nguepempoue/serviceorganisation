package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.AccountRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.Account;
import com.b2i.serviceorganisation.model.AccountType;
import com.b2i.serviceorganisation.model.AccountingClass;
import com.b2i.serviceorganisation.model.User;
import com.b2i.serviceorganisation.repository.AccountRepository;
import com.b2i.serviceorganisation.repository.AccountTypeRepository;
import com.b2i.serviceorganisation.repository.AccountingClassRepository;
import com.b2i.serviceorganisation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService{


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountingClassRepository accountingClassRepository;


    // CREATE ACCOUNT FOR A USER
    @Override
    public Account createAccount(Long idUser, Long idAccountType) {

        // NEW ACCOUNT
        Account a = new Account();
        Account toAdd;

        // ACCOUNT TYPE
        Optional<AccountType> type = accountTypeRepository.findById(idAccountType);

        // USER
        Optional<User> user = userRepository.findById(idUser);

        // CLASS
        Optional<AccountingClass> accountingClass = accountingClassRepository.findById(1L);

        try {
            if(!type.isPresent()) {
                // return ResponseHandler.generateNotFoundResponse("Account type not found !");
                throw new Exception("Account type not found !");
            } else if(!user.isPresent()) {
                // return ResponseHandler.generateNotFoundResponse("User not found !");
                throw new Exception("User not found !");
            }
            else {
                User u = user.get();
                AccountType t = type.get();

                a.setLabel(u.getFirstName().toUpperCase() + "_" + u.getLastName().toUpperCase() + "_" + t.getLabel() + "_ACCOUNT");
                a.setType(t);
                a.setBalance(0L);

                // LINK IT TO CLASS 1
                if(accountingClass.isPresent()) {
                    a.setAccountingClass(accountingClass.get());
                }

                toAdd = accountRepository.save(a);
            }
            return toAdd;
            // return ResponseHandler.generateResponse("This account has properly been created !", HttpStatus.CREATED, a);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }


    // CREATE ACCOUNT
    @Override
    public ResponseEntity<Object> create(Long idAccountType) {

        // NEW ACCOUNT
        Account a = new Account();

        // GET ACCOUNT TYPE
        Optional<AccountType> t = accountTypeRepository.findById(idAccountType);

        // GET ACCOUNTING CLASS
        Optional<AccountingClass> c = accountingClassRepository.findById(1L);

        try {

            if(!t.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Type not found !");
            }

            else if(!c.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Accounting class not found !");
            }

            else {
                a.setType(t.get());
                a.setAccountingClass(c.get());
                a.setLabel(t.get().getLabel() + "_ACCOUNT");
                a.setBalance(0L);
            }

            a = accountRepository.save(a);

            AccountRequest ar = new AccountRequest(a.getId(), a.getAccountNumber(), a.getLabel(), a.getBalance());

            return ResponseHandler.generateResponse("Account created", HttpStatus.OK, ar);

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL ACCOUNTS
    @Override
    public ResponseEntity<Object> findAllAccounts() {

        // GET ALL ACCOUNTS
        List<Account> accountList;

        try {
            accountList = accountRepository.findAll();

            if(accountList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("There's no account !");
            }
            else {
                List<AccountRequest> requestList = new ArrayList<>();
                accountList.forEach(a -> {
                    AccountRequest ar = new AccountRequest(a.getId(), a.getAccountNumber(), a.getLabel(), a.getBalance());
                    requestList.add(ar);
                });
                return ResponseHandler.generateResponse("Accounts List", HttpStatus.OK, requestList);
            }
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE ACCOUNT
    @Override
    public ResponseEntity<Object> deleteAccountById(Long id) {

        // GET ACCOUNT
        Optional<Account> account = accountRepository.findById(id);

        try {
            if(!account.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Account not found !");
            }
            else {
                accountRepository.delete(account.get());
                return ResponseHandler.generateResponse("The account " + id + " has properly been deleted !",
                        HttpStatus.OK, null);
            }
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ACCOUNT BY ID
    @Override
    public ResponseEntity<Object> findAccountById(Long id){

        // GET ACCOUNT
        Optional<Account> account = accountRepository.findById(id);

        try {
            return account.map(a -> {
                AccountRequest ar = new AccountRequest(a.getId(), a.getAccountNumber(), a.getLabel(), a.getBalance());
                return ResponseHandler.generateResponse("Account " + id, HttpStatus.OK, ar);
            })
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Account not found !"));
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL ACCOUNTS
    @Override
    public ResponseEntity<Object> countAllAccounts() {

        Long accountsNumber = accountRepository.count();
        return ResponseHandler.generateResponse("Accounts number", HttpStatus.OK, accountsNumber);
    }


    // SET BALANCE
    @Override
    public ResponseEntity<Object> setBalance(Long id, Long amount) {

        // GET ACCOUNT
        Optional<Account> account = accountRepository.findById(id);


        try {
            if(!account.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Account not found !");
            }

            if(amount == null) {
                throw new Exception("Amount can't be null !");
            }
            else if(amount == 0) {
                throw new Exception("Amount can't be equal to 0 !");
            }
            else {
                Account a = account.get();
                a.setBalance(a.getBalance() + amount);
                return ResponseHandler.generateResponse("Account balance updated !", HttpStatus.OK, accountRepository.save(a));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }
}
