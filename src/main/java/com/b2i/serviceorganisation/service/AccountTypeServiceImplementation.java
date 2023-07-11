package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.model.AccountType;
import com.b2i.serviceorganisation.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeServiceImplementation implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    // CREATE ACCOUNT TYPE
    @Override
    public AccountType createAccountType(AccountType userType) throws Exception {

        // SAVING ACCOUNT TYPE
        AccountType accountType = new AccountType();
        try {
            if(userType.getLabel() == null) {
                throw new Exception("Account userType label can't be null !");
            }
            else {
                accountType.setLabel(userType.getLabel());
                if(userType.getDescription() == null) {
                    accountType.setDescription(userType.getLabel());
                }
                else {
                    accountType.setDescription(userType.getDescription());
                }
            }

            accountType = accountTypeRepository.save(accountType);
            return accountType;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // READ ACCOUNT TYPE
    @Override
    public List<AccountType> findAllAccountTypes() {

        // GET ALL ACCOUNT TYPES
        List<AccountType> accountTypes = new ArrayList<>();

        try {
            accountTypes = accountTypeRepository.findAll();
            return accountTypes;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    // UPDATE ACCOUNT TYPE
    @Override
    public AccountType updateAccountType(Long id, AccountType userType) throws Exception {

        // GET ACCOUNT TYPE TO UPDATE
        Optional<AccountType> accountType = accountTypeRepository.findById(id);

        try {
            if(!accountType.isPresent()) {
                throw new Exception("Account userType not found !");
            }

            AccountType typeToUpdate = accountType.get();

            if(userType.getLabel() == null) {
                throw new Exception("Account userType label can't be null !");
            }
            else {
                typeToUpdate.setLabel(userType.getLabel());
                if(userType.getDescription() == null) {
                    typeToUpdate.setDescription(userType.getLabel());
                }
                else {
                    typeToUpdate.setDescription(userType.getDescription());
                }
            }

            typeToUpdate = accountTypeRepository.save(typeToUpdate);
            return typeToUpdate;

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // DELETE ACCOUNT TYPE
    @Override
    public Boolean deleteAccountType(Long id) throws Exception {

        // GET ACCOUNT TYPE
        Optional<AccountType> userType = accountTypeRepository.findById(id);

        try {
            if(!userType.isPresent()) {
                throw new Exception("Account userType not found !");
            }

            AccountType toDelete = userType.get();
            accountTypeRepository.delete(toDelete);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return false;

        }
    }


    // COUNT ALL ACCOUNT TYPES
    @Override
    public Long countAllAccountTypes() {
        return accountTypeRepository.count();
    }


    // FIND ACCOUNT TYPE BY ID
    @Override
    public AccountType findAccountTypeById(Long id) {

        // GET ACCOUNT TYPE
        Optional<AccountType> userType = accountTypeRepository.findById(id);

        // TESTS
        try {
            if(!userType.isPresent()) {
                throw new Exception("Account userType not found !");
            }
            else {
                return userType.get();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
}
