package ru.ibs.gisgmp.charge.organization;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.validation.RegExpValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.gisgmp.common.validation.Validator;

import java.util.List;

public class Bik extends StringBased{

    @Override
    protected List<ValidationResult> validate(String text) {
        return FORMAT.validate(text);
    }

    public static final Validator<String> FORMAT = new RegExpValidator("[0-9]{9}", "", "format");

    public static List<ValidationResult> validateFormat(String str){
        return RegExpValidator.validate(str, "[0-9]{9}", "", "format");
    }
}
