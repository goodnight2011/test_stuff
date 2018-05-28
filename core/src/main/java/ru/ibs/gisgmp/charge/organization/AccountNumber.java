package ru.ibs.gisgmp.charge.organization;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.validation.RegExpValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.List;

public class AccountNumber extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validateFormat(text);
    }

    public static List<ValidationResult> validateFormat(String str){
       return RegExpValidator.validate(str, "[0-9]{20}", "", "format");
    }
}
