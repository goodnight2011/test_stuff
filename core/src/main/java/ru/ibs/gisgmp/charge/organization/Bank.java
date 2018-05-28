package ru.ibs.gisgmp.charge.organization;

import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.List;

@FieldConst
public class Bank {
    private String name;
    private Bik bik;
    private AccountNumber correspondentBankAccount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bik getBik() {
        return bik;
    }

    public void setBik(Bik bik) {
        this.bik = bik;
    }

    public AccountNumber getCorrespondentBankAccount() {
        return correspondentBankAccount;
    }

    public void setCorrespondentBankAccount(AccountNumber correspondentBankAccount) {
        this.correspondentBankAccount = correspondentBankAccount;
    }

    public static List<ValidationResult> validateBik(Bank bank){
        return null;
//        return NonNullValidator.validate(bank.getBik(), new DelegateValidator<Bank, Bik>(BIK, Bik::validate, Bank::getBik), BIK, BIK + ".empty");
    }

    public static List<ValidationResult> validate(Bank bank) {
//        Validator<Bank> bik = NonNullValidator.withNonNull(
//                new DelegateValidator<>(BIK, Bik::validate, Bank::getBik),
//                BIK,
//                BIK + ".empty"
//        ) ;
//
//        Validator<Bank> corrAcc = new DelegateValidator<>(CORRESPONDENT_BANK_ACCOUNT,
//                AccountNumber::validate, Bank::getCorrespondentBankAccount);
//
//        return CompositeValidator.validate(bank, bik, corrAcc);
        return null;
    }
}
