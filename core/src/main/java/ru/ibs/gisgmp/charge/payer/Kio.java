package ru.ibs.gisgmp.charge.payer;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.validation.RegExpValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.List;

public class Kio extends StringBased{

    @Override
    protected List<ValidationResult> validate(String str) {
        return RegExpValidator.validate(str, "[0-9]{5}", "", ".format");
    }
}
