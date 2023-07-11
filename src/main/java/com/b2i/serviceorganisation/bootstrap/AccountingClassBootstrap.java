package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.model.AccountingClass;
import com.b2i.serviceorganisation.service.AccountingClassServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class AccountingClassBootstrap {

    private final AccountingClassServiceImplementation implementation;


    public AccountingClassBootstrap(AccountingClassServiceImplementation implementation) {
        this.implementation = implementation;
    }

    public void seed() throws Exception {
        if(implementation.countAllAccountingClasses() == 0L) {

            implementation.createAccountingClass(new AccountingClass("COMPTES DE CAPITAUX", 1L));
            implementation.createAccountingClass(new AccountingClass("COMPTES D'IMMOBILISATIONS", 2L));
            implementation.createAccountingClass(new AccountingClass("COMPTES DE STOCKS ET EN COURS", 3L));
            implementation.createAccountingClass(new AccountingClass("COMPTES DE TIERS", 4L));
            implementation.createAccountingClass(new AccountingClass("COMPTES FINANCIERS", 5L));
            implementation.createAccountingClass(new AccountingClass("COMPTES DE CHARGES", 6L));
            implementation.createAccountingClass(new AccountingClass("COMPTES DE PRODUITS", 7L));
            implementation.createAccountingClass(new AccountingClass("COMPTES SPECIAUX", 8L));
        }
    }
}
