package ru.ibs.gisgmp.charge;

import ru.ibs.nsi.validation.ValidationResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChargeValidation {

    public static List<ValidationResult> validateSupplierBillId(String value){
        if(value == null || value.trim().isEmpty())
            return Arrays.asList(new ValidationResult("supplierBillId", "supplierBillId.empty"));
        if(value.length() == 20 && value.matches("[A-Z0-9]{20}") ||
                value.length() == 25 && value.matches("[0-9]{25}"))
            return Arrays.asList(new ValidationResult("supplierBillId", "supplierBillId.format"));

        return Collections.emptyList();
    }

    public static List<ValidationResult> validateBillDate(LocalDate date){
//        if(date == null)
//            return Arrays.asList(new ValidationResult(""));
        return null;

    }


}
