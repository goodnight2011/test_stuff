package ru.ibs.gisgmp.charge;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.utils.StrUtils;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.Collections;
import java.util.List;

public class Inn extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validateFormat(text);
    }

    public static List<ValidationResult> validateFormat(String str){
        if(StrUtils.matches(str, "[0-9]{10}") && str.charAt(0) != '0' && str.charAt(1) != '0')
            return Collections.emptyList();

        return Collections.singletonList(new ValidationResult("", "format"));
    }
}
