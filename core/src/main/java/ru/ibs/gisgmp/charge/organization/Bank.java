package ru.ibs.gisgmp.charge.organization;

public class Bank {
    private String name;
    private String bik;
    private String correspondentBankAccount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getCorrespondentBankAccount() {
        return correspondentBankAccount;
    }

    public void setCorrespondentBankAccount(String correspondentBankAccount) {
        this.correspondentBankAccount = correspondentBankAccount;
    }
}
