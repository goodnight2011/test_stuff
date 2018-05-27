package ru.ibs.gisgmp.charge.requisites;

import ru.ibs.common.StringBased;
import ru.ibs.nsi.validation.RegExpValidator;
import ru.ibs.nsi.validation.ValidationResult;

import java.util.List;

public class PaymentType extends StringBased{

    @Override
    protected List<ValidationResult> validate(String text) {
        return validateFormat(text);
    }

    public static List<ValidationResult> validateFormat(String str){
        return RegExpValidator.validate(str, "(.{2}|0)", "", "format");
    }
}
