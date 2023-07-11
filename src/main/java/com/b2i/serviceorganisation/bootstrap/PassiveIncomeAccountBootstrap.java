package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.model.Account;
import com.b2i.serviceorganisation.repository.AccountRepository;
import com.b2i.serviceorganisation.service.AccountServiceImplementation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassiveIncomeAccountBootstrap {

    private final AccountServiceImplementation accountServiceImplementation;

    private final AccountRepository accountRepository;

    public PassiveIncomeAccountBootstrap(AccountServiceImplementation accountServiceImplementation, AccountRepository accountRepository) {
        this.accountServiceImplementation = accountServiceImplementation;
        this.accountRepository = accountRepository;
    }

    public void seed() {

        List<Account> accountList = accountRepository.findAll();

        // TEST IF EXISTS ACCOUNT OF TYPE "FONDS DE REVENUS PASSIFS"
        boolean test = false;
        for(Account a : accountList) {
            if(a.getType().getId().equals(5L)) { test = true; break; }
        }

        // CREATE ACCOUNT OF TYPE "FONDS DE REVENUS PASSIFS"
        if(!test) { accountServiceImplementation.create(5L); }
    }
}
