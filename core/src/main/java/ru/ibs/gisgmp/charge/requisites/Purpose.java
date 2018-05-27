package ru.ibs.gisgmp.charge.requisites;

import ru.ibs.common.StringBased;
import ru.ibs.nsi.validation.ValidationResult;
import ru.ibs.nsi.validation.Validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Purpose extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validateFormat(text);
    }

    public static final List<String> POSSIBLE_VALUES = Arrays.asList("ТП", "ЗД", "БФ", "ТР", "РС", "ОТ", "РТ",
            "ПБ", "ПР", "АП", "АР", "ИН", "ТЛ", "ЗТ", "ДЕ", "ПО", "КТ", "ИД", "ИП", "ТУ", "БД", "КП", "ВУ", "ДК",
            "ПК", "КК", "ТК", "ПД", "КВ", "00", "0");

    public static List<ValidationResult> validateFormat(String str){
       return POSSIBLE_VALUES.contains(str) ? Collections.emptyList() :
            Collections.singletonList(new ValidationResult("", "format"));
    }
}
