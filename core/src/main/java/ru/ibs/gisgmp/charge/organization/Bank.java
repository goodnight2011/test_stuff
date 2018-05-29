package ru.ibs.gisgmp.charge.organization;

import ru.ibs.gisgmp.common.validation.CompositeValidator;
import ru.ibs.gisgmp.common.validation.DelegateValidator;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.Arrays;
import java.util.List;

import static ru.ibs.gisgmp.charge.organization.BankFields.BIK;
import static ru.ibs.gisgmp.charge.organization.BankFields.CORRESPONDENT_BANK_ACCOUNT;

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
        DelegateValidator<Bank, Bik> validator = new DelegateValidator<>(BIK, bik -> bik.validate(), abank -> abank.getBik());
        return NonNullValidator.validate(bank, validator, BIK, BIK + ".empty");
    }

    public static List<ValidationResult> validateCorrAcc(Bank bank){
        DelegateValidator<Bank, AccountNumber> validator = new DelegateValidator<>(CORRESPONDENT_BANK_ACCOUNT,
                accnum -> accnum.validate(accnum.getString()), bk -> bk.getCorrespondentBankAccount());
        return validator.validate(bank);
    }
    public static List<ValidationResult> validate(Bank abank) {
        return CompositeValidator.validate(abank, Arrays.asList(Bank::validateBik, Bank::validateCorrAcc));
    }
}
