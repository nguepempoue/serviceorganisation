package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.model.AccountingClass;
import com.b2i.serviceorganisation.repository.AccountingClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountingClassServiceImplementation implements AccountingClassService {


    @Autowired
    private AccountingClassRepository accountingClassRepository;


    // CREATE ACCOUNTING CLASS
    @Override
    public AccountingClass createAccountingClass(AccountingClass accountingClass) throws Exception {

        try {
            if(accountingClass.getNumber() == 0L || accountingClass.getNumber() == null) {
                throw new Exception("Accounting class number can't be null or equal to 0 !");
            }
            else {
                return accountingClassRepository.save(accountingClass);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // FIND ALL ACCOUNTING CLASS
    @Override
    public List<AccountingClass> findAllAccountingClasses(){

        // GET CLASSES
        List<AccountingClass> classes = accountingClassRepository.findAll();

        if(classes.isEmpty()) {
            return null;
        }
        else return classes;
    }


    // UPDATE ACCOUNTING CLASS
    @Override
    public AccountingClass updateAccountingClass(Long id, AccountingClass accountingClass) throws Exception {

        // GET CLASS
        Optional<AccountingClass> optionalClass = accountingClassRepository.findById(id);
        AccountingClass c = new AccountingClass();

        // TRY
        try {
            if(!optionalClass.isPresent()) {
                throw new Exception("Accounting class not found !");
            }
            else {
                c = optionalClass.get();
                c.setNumber(accountingClass.getNumber());
                c.setLabel(accountingClass.getLabel());

                return accountingClassRepository.save(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // DELETE ACCOUNTING CLASS
    @Override
    public Boolean deleteAccountingClassById(Long id) throws Exception {

        // GET CLASS
        Optional<AccountingClass> c = accountingClassRepository.findById(id);

        try {
            if(!c.isPresent()) {
                throw new Exception("Account not found !");
            }
            else {
                accountingClassRepository.deleteById(id);
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }


    // COUNTING ALL CLASSES
    @Override
    public Long countAllAccountingClasses() {
        return accountingClassRepository.count();
    }
}
