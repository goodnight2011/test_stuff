package ru.ibs.gisgmp.charge.payer;

import ru.ibs.gisgmp.charge.Inn;
import ru.ibs.gisgmp.charge.Kpp;
import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static ru.ibs.gisgmp.charge.payer.UlPayerIdentifierFields.*;

@FieldConst
public class UlPayerIdentifier {
    private ResidentType residentType;
    private Inn inn;
    private Kpp kpp;
    private Kio kio;

    public ResidentType getResidentType() {
        return residentType;
    }

    public void setResidentType(ResidentType residentType) {
        this.residentType = residentType;
    }

    public Inn getInn() {
        return inn;
    }

    public void setInn(Inn inn) {
        this.inn = inn;
    }

    public Kpp getKpp() {
        return kpp;
    }

    public void setKpp(Kpp kpp) {
        this.kpp = kpp;
    }

    public Kio getKio() {
        return kio;
    }

    public void setKio(Kio kio) {
        this.kio = kio;
    }

    public static List<ValidationResult> validate(UlPayerIdentifier id){
        List<ValidationResult> ret = new ArrayList<>();
        ret.addAll(NonNullValidator.validate(id.residentType, RESIDENT_TYPE, RESIDENT_TYPE + ".empty"));
        if(ret.isEmpty()){
            boolean checkInn = id.residentType.equals(ResidentType.RESIDENT)
                    || id.residentType.equals(ResidentType.NOT_RESIDENT_WITH_INN);

            boolean checkKio = id.residentType.equals(ResidentType.NOT_RESIDENT_WITH_KIO);
            ret.addAll(ArrUtils.concat(Arrays.asList(
                    (checkInn ? NonNullValidator.validate(id.inn, val -> val.validate(), INN, INN + ".empty"):
                            Collections.emptyList()),
                    (checkKio ? NonNullValidator.validate(id.kio, val -> val.validate(), KPP, KPP + ".empty") :
                            Collections.emptyList()),
                    NonNullValidator.validate(id.kpp, val -> val.validate(), KIO, KIO + ".empty")
            )));
        }

        return ret;
    }
}

