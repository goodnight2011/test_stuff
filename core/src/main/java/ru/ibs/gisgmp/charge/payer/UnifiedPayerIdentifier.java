package ru.ibs.gisgmp.charge.payer;

import ru.ibs.gisgmp.charge.Inn;
import ru.ibs.gisgmp.charge.Kpp;
import ru.ibs.gisgmp.charge.payer.fl.AltPayerIdentifier;
import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static ru.ibs.gisgmp.charge.payer.UnifiedPayerIdentifierFields.*;

@FieldConst
public class UnifiedPayerIdentifier {
    private UlType ulType;
    private UlPayerIdentifier ulPayerIdentifier;
    private AltPayerIdentifier altPayerIdentifier;

    public AltPayerIdentifier getAltPayerIdentifier() {
        return altPayerIdentifier;
    }

    public void setAltPayerIdentifier(AltPayerIdentifier altPayerIdentifier) {
        this.altPayerIdentifier = altPayerIdentifier;
    }

    public UlType getUlType() {
        return ulType;
    }

    public void setUlType(UlType ulType) {
        this.ulType = ulType;
    }

    public UlPayerIdentifier getUlPayerIdentifier() {
        return ulPayerIdentifier;
    }

    public void setUlPayerIdentifier(UlPayerIdentifier ulPayerIdentifier) {
        this.ulPayerIdentifier = ulPayerIdentifier;
    }

    public static List<ValidationResult> validate(UnifiedPayerIdentifier id){
        List<ValidationResult> ret = new ArrayList<>();
        ret.addAll(NonNullValidator.validate(id.ulType, UL_TYPE, UL_TYPE + ".empty"));
        if(ret.isEmpty()){
            if(id.ulType.equals(UlType.FL)){
                ret.addAll(NonNullValidator.validate(id.altPayerIdentifier, AltPayerIdentifier::validate, ALT_PAYER_IDENTIFIER, ALT_PAYER_IDENTIFIER + ".empty"));
                if(id.ulPayerIdentifier != null)
                    ret.add( new ValidationResult(UL_PAYER_IDENTIFIER, UL_PAYER_IDENTIFIER + ".ul_type_match") );
            }
            else{
                ret.addAll(NonNullValidator.validate(id.ulPayerIdentifier, UlPayerIdentifier::validate, UL_PAYER_IDENTIFIER, UL_PAYER_IDENTIFIER + ".empty"));
                if(id.altPayerIdentifier != null)
                    ret.add(new ValidationResult(ALT_PAYER_IDENTIFIER, ALT_PAYER_IDENTIFIER + ".ul_type_match"));
            }
        }
        return ret;
    }
}
