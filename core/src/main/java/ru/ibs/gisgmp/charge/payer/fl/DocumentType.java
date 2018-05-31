package ru.ibs.gisgmp.charge.payer.fl;

import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.Arrays;
import java.util.List;
import static ru.ibs.gisgmp.charge.payer.fl.DocumentTypeFields.*;

@FieldConst
public class DocumentType {
    private String code;
    private String name;

    public DocumentType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<ValidationResult> validate(DocumentType doctype){
       return ArrUtils.concat(Arrays.asList(
               NonNullValidator.notEmptyString(doctype.code, CODE, CODE + ".empty") ,
               NonNullValidator.notEmptyString(doctype.name, NAME, NAME + ".empty")
       ));
    }
}
