package ru.ibs.gisgmp.charge.organization;

import ru.ibs.common.StringBased;
import ru.ibs.nsi.validation.RegExpValidator;
import ru.ibs.nsi.validation.ValidationResult;
import ru.ibs.nsi.validation.Validator;

import java.text.Format;
import java.util.List;

public class Oktmo extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return FORMAT.validate(text);
    }

    public static final Validator<String> FORMAT = new RegExpValidator("([0-9]{8}|[0-9]{11}|0)", "", "format");

    public static List<ValidationResult> validateFormat(String str){
       return  RegExpValidator.validate(str, "([0-9]{8}|[0-9]{11}|0)", "", "format");
    }
}
