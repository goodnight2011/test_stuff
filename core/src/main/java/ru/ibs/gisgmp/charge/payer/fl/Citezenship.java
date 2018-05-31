package ru.ibs.gisgmp.charge.payer.fl;

import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.Arrays;
import java.util.List;
import static ru.ibs.gisgmp.charge.payer.fl.CitezenshipFields.*;

@FieldConst
public class Citezenship {
    private String countryCode;
    private String countryName;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public static List<ValidationResult> validate(Citezenship citezenship){
       return ArrUtils.concat(Arrays.asList(
               NonNullValidator.notEmptyString(citezenship.countryCode, COUNTRY_CODE, COUNTRY_CODE + ".emtpy"),
               NonNullValidator.notEmptyString(citezenship.countryName, COUNTRY_NAME, COUNTRY_NAME + ".emtpy")
       )) ;
    }
}
