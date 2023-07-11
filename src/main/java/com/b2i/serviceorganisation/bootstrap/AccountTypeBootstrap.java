package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.model.AccountType;
import com.b2i.serviceorganisation.service.AccountTypeServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class AccountTypeBootstrap {

    private final AccountTypeServiceImplementation accountTypeServiceImplementation;

    public AccountTypeBootstrap(AccountTypeServiceImplementation accountTypeServiceImplementation) {
        this.accountTypeServiceImplementation = accountTypeServiceImplementation;
    }

    public void seed() throws Exception {

        if(accountTypeServiceImplementation.countAllAccountTypes() == 0L) {
            accountTypeServiceImplementation.createAccountType(new AccountType("FONDS DE SOLIDARITÉ", "FONDS DE SOLIDARITÉ"));
            accountTypeServiceImplementation.createAccountType(new AccountType("FONDS DE TONTINE", "FONDS DE TONTINE"));
            accountTypeServiceImplementation.createAccountType(new AccountType("FONDS D'INVESTISSEMENT", "FONDS D'INVESTISSEMENT"));
            accountTypeServiceImplementation.createAccountType(new AccountType("FONDS SMS TRADE", "FONDS SMS TRADE"));
            accountTypeServiceImplementation.createAccountType(new AccountType("FONDS DE REVENUS PASSIFS", "FONDS DE REVENUS PASSIFS"));
            accountTypeServiceImplementation.createAccountType(new AccountType("FONDS PLACEMENT MUTUALISÉS", "FONDS PLACEMENT MUTUALISÉS"));
        }
    }
}
