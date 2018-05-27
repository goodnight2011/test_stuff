package ru.ibs.gisgmp.charge;

import ru.ibs.common.StringBased;
import ru.ibs.nsi.validation.RegExpValidator;
import ru.ibs.nsi.validation.ValidationResult;
import ru.ibs.nsi.validation.Validator;

import java.text.Format;
import java.util.List;

public class Ogrn extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validateFormat(text);
    }

    public static List<ValidationResult> validateFormat(String str){
       return RegExpValidator.validate(str, "[0-9]{13}", "", "format") ;
    }
}
