package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.model.AccountType;

import java.util.List;

public interface AccountTypeService {

    // CRUD OPERATIONS //
    AccountType createAccountType(AccountType type) throws Exception;

    List<AccountType> findAllAccountTypes();

    AccountType updateAccountType(Long id, AccountType type) throws Exception;

    Boolean deleteAccountType(Long id) throws Exception;


    // MORE //
    Long countAllAccountTypes();

    AccountType findAccountTypeById(Long id) throws Exception;
}
