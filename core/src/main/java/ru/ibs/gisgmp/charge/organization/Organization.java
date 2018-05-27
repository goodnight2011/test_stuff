package ru.ibs.gisgmp.charge.organization;

import ru.ibs.gisgmp.charge.Inn;
import ru.ibs.gisgmp.charge.Kpp;
import ru.ibs.gisgmp.charge.Ogrn;

public class Organization {
    private String name;
    private Inn inn;
    private Kpp kpp;
    private Ogrn ogrn;
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inn getInn() {
        return inn;
    }

    public void setInn(Inn inn) {
        this.inn = inn;
    }

    public Kpp getKpp() {
        return kpp;
    }

    public void setKpp(Kpp kpp) {
        this.kpp = kpp;
    }

    public Ogrn getOgrn() {
        return ogrn;
    }

    public void setOgrn(Ogrn ogrn) {
        this.ogrn = ogrn;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
