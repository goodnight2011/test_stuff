package ru.ibs.gisgmp.charge.organization;

import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.List;
import static ru.ibs.gisgmp.charge.organization.AccountFields.*;

@FieldConst
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

    public static List<ValidationResult> validate(Account account){
        return ArrUtils.concat(ArrUtils.arrToList(
                NonNullValidator.validate(account.getBank(), bank -> Bank.validate(bank), BANK, BANK + ".empty"),
                NonNullValidator.validate(account.getAccountNumber(), accn -> AccountNumber.validateFormat(accn.getString()),
                        ACCOUNT_NUMBER, ACCOUNT_NUMBER + ".empty")
        ));
    }
}
