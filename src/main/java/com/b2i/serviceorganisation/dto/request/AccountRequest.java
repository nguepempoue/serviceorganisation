package com.b2i.serviceorganisation.dto.request;

public class AccountRequest {

    private Long id;

    private String accountNumber;

    private String label;

    private Long balance;

    public AccountRequest() {

    }

    public AccountRequest(Long id, String accountNumber, String label, Long balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.label = label;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "AccountRequest{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", label='" + label + '\'' +
                ", balance=" + balance +
                '}';
    }
}
