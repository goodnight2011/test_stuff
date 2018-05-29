package ru.ibs.gisgmp.charge;

import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChargeValidation {

    public static List<ValidationResult> validateSupplierBillId(String value){
//        if(value == null || value.trim().isEmpty())
//            return Arrays.asList(new ValidationResult(ChargeFields.SUPPLIER_BILL_ID, ChargeFields.SUPPLIER_BILL_ID + ".empty"));
//        if(value.length() == 20 && value.matches("[A-Z0-9]{20}") ||
//                value.length() == 25 && value.matches("[0-9]{25}"))
//            return Arrays.asList(new ValidationResult(ChargeFields.SUPPLIER_BILL_ID, ChargeFields.SUPPLIER_BILL_ID + ".format"));

        return Collections.emptyList();
    }

    public static List<ValidationResult> validateBillDate(Date date){
//        if(date == null)
//            return Arrays.asList(new ValidationResult(""));
        String another = ChargeFields.BILL_DATE;
        return null;

    }


}
