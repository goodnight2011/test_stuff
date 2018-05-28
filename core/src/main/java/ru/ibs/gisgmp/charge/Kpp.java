package ru.ibs.gisgmp.charge;

import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.Collections;
import java.util.List;

public class Kpp extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validateFormat(text);
    }

    public static List<ValidationResult> validateFormat(String str){

        if(str.length() == 9){
            String first = str.substring(0,4);
            String middle = str.substring(4,6);
            String rest = str.substring(6, str.length());

//            if(first.matches("[0-9]{5}")
//                    && middle.matches("[0-9A-Z]{2}")
//                    && rest.matches("[0-9]{3}")
//                    && !str.substring(0, 2).equals("00"))
//                return Collections.emptyList();
        }

        return Collections.singletonList(new ValidationResult("", "format"));
    }
}
