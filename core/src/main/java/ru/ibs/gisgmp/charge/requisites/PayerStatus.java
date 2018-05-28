package ru.ibs.gisgmp.charge.requisites;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.Collections;
import java.util.List;

public class PayerStatus extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validate(text);
    }

    public static final List<String> POSSIBLE_VALUES = null;
/*    IntStream.range(1, 29).
            mapToObj(cnt -> String.format("%02d", cnt)).
            collect(Collectors.toList());*/

    public static List<ValidationResult> validateFormat(String str){
       return  POSSIBLE_VALUES.contains(str) ? Collections.emptyList() :
            Collections.singletonList(new ValidationResult("", "format"));
    }
}