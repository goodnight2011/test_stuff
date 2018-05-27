package ru.ibs.gisgmp.charge.requisites.period;

import ru.ibs.nsi.validation.ValidationResult;
import ru.ibs.processor.FieldConst;
import static ru.ibs.gisgmp.charge.requisites.period.TaxPeriodFields.*;

import java.util.Collections;
import java.util.List;

@FieldConst
public class TaxPeriod {
    private TypedPeriod typedPeriod;
    private DatePeriod datePeriod;

    public TypedPeriod getTypedPeriod() {
        return typedPeriod;
    }

    public void setTypedPeriod(TypedPeriod typedPeriod) {
        if(typedPeriod != null)
            this.datePeriod =  null;
        this.typedPeriod = typedPeriod;
    }

    public DatePeriod getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(DatePeriod datePeriod) {
        if(datePeriod != null)
            this.typedPeriod = null;
        this.datePeriod = datePeriod;
    }

    public static List<ValidationResult> validateTypedPeriod(TaxPeriod period){
        if(period.getTypedPeriod() != null)
            return TypedPeriod.validate(period.getTypedPeriod());
        else if(period.getDatePeriod() != null)
            return DatePeriod.validateDate(period.getDatePeriod());
        else return Collections.singletonList(new ValidationResult("", ".empty"));
    }
}
