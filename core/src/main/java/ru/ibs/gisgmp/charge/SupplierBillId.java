package ru.ibs.gisgmp.charge;

import ru.ibs.gisgmp.common.validation.RegExpValidator;
import ru.ibs.gisgmp.common.StringBased;
import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.List;

public class SupplierBillId extends StringBased{
    @Override
    protected List<ValidationResult> validate(String text) {
        return validateFormat(text);
    }

    public static List<ValidationResult> validateFormat1(String str){
        return RegExpValidator.validate(str, "[0-9A-Z]{20}", "", "format1");
    }

    public static List<ValidationResult> validateFormat2(String str){
       return  RegExpValidator.validate(str, "[0-9]{25}", "", "format2");
    }

    public static List<ValidationResult> validateFormat(String str){
//       return CorrectIfAnyValidator.validate(str, SupplierBillId::validateFormat1, SupplierBillId::validateFormat2);
        return null;
    }

}
