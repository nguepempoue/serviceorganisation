package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.model.AccountingClass;

import java.util.List;

public interface AccountingClassService {

    // CRUD OPERATIONS //
    AccountingClass createAccountingClass(AccountingClass accountingClass) throws Exception;

    List<AccountingClass> findAllAccountingClasses();

    AccountingClass updateAccountingClass(Long id, AccountingClass accountingClass) throws Exception;

    Boolean deleteAccountingClassById(Long id) throws Exception;

    Long countAllAccountingClasses();
}
