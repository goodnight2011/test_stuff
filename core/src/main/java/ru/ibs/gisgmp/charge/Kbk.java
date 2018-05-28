package ru.ibs.gisgmp.charge;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.validation.RegExpValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.List;

public class Kbk extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
       return validateFormat(text);
    }

    public static List<ValidationResult> validateFormat(String str){
        return RegExpValidator.validate(str, "([0-9a-zA-Zа-яА-Я]{20}|0)", "", "format");
    }
}
