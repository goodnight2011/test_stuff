package ru.ibs.gisgmp.charge.organization;

public class Account {
    private Bank bank;
    private AccountNumber accountNumber;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }
}
