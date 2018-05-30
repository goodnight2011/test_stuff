package ru.ibs.gisgmp.charge.requisites;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.utils.DateUtils;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PayerStatus extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validate(text);
    }

    public static final List<String> POSSIBLE_VALUES = createValues();

    private static List<String> createValues() {
        List<String> ret = new ArrayList<>();
        for(int i = 1 ; i <= 29; i++ )
            ret.add(DateUtils.pad2digits(i));
        return ret;
    }

    public static List<ValidationResult> validateFormat(String str){
       return  POSSIBLE_VALUES.contains(str) ? Collections.emptyList() :
            Collections.singletonList(new ValidationResult("", "format"));
    }
}
