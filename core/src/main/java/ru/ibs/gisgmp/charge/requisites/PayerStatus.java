package ru.ibs.gisgmp.charge.requisites;

import ru.ibs.common.StringBased;
import ru.ibs.nsi.validation.ValidationResult;
import ru.ibs.nsi.validation.Validator;

import java.text.Format;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PayerStatus extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validate(text);
    }

    public static final List<String> POSSIBLE_VALUES = IntStream.range(1, 29).
            mapToObj(cnt -> String.format("%02d", cnt)).
            collect(Collectors.toList());

    public static List<ValidationResult> validateFormat(String str){
       return  POSSIBLE_VALUES.contains(str) ? Collections.emptyList() :
            Collections.singletonList(new ValidationResult("", "format"));
    }
}
