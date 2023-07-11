package com.b2i.serviceorganisation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private String label;

    private Long balance = 0L;

    @JsonIgnore
    @ManyToOne
    private AccountType type;

    @JsonIgnore
    @ManyToOne
    private AccountingClass accountingClass;

    public Account() {

    }

    public Account(Long id, String accountNumber, String label, Long balance, AccountType type, AccountingClass accountingClass) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.label = label;
        this.balance = balance;
        this.type = type;
        this.accountingClass = accountingClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountingClass getAccountingClass() {
        return accountingClass;
    }

    public void setAccountingClass(AccountingClass accountingClass) {
        this.accountingClass = accountingClass;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", label='" + label + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                ", accountingClass=" + accountingClass +
                '}';
    }
}
