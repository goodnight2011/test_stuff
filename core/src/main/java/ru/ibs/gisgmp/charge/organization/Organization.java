package ru.ibs.gisgmp.charge.organization;

import ru.ibs.gisgmp.charge.Inn;
import ru.ibs.gisgmp.charge.Kpp;
import ru.ibs.gisgmp.charge.Ogrn;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static ru.ibs.gisgmp.charge.organization.OrganizationFields.*;

@FieldConst
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

    public static List<ValidationResult> validate(Organization organization){
        List<ValidationResult> res = new ArrayList<>();
        res.addAll(NonNullValidator.notEmptyString(organization.getName(), NAME, NAME + ".empty"));
        res.addAll(NonNullValidator.validate(organization.getInn(), inn -> inn.validate(), INN, INN + ".empty"));
        res.addAll(NonNullValidator.validate(organization.getKpp(), kpp -> kpp.validate(), KPP, KPP + ".empty"));
        res.addAll(organization.getOgrn() != null ? organization.getOgrn().validate() : Collections.emptyList());
        res.addAll(NonNullValidator.validate(organization.getAccount(), Account::validate, ACCOUNT, ACCOUNT + ".empty"));
        return res;
    }
}
