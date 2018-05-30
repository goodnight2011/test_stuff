package ru.ibs.gisgmp.charge.requisites.period;

import ru.ibs.gisgmp.common.utils.DateUtils;
import ru.ibs.gisgmp.common.validation.CompositeValidator;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.ibs.gisgmp.charge.requisites.period.TypedPeriodFields.*;

@FieldConst
public class TypedPeriod {
    private PeriodType type;
    private int periodNumber;
    private int year;

    public PeriodType getType() {
        return type;
    }

    public void setType(PeriodType type) {
        this.type = type;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static List<ValidationResult> validateType(TypedPeriod period){
        return NonNullValidator.validate(period.getType(), TYPE, TYPE + ".empty");
    }

    public static List<ValidationResult> validatePeriod(TypedPeriod period){
        List<ValidationResult> failedResult = Collections.singletonList(new ValidationResult(PERIOD_NUMBER, PERIOD_NUMBER + ".invalid"));
        switch(period.getType()){
            case MONTH:
                if(period.getPeriodNumber() < 1 || period.getPeriodNumber() > 12)
                    return failedResult;
                break;
            case QUARTER:
                if(period.getPeriodNumber() < 1 || period.getPeriodNumber() > 4)
                    return failedResult;
                break;
            case HALF:
                if(period.getPeriodNumber() < 1 || period.getPeriodNumber() > 2)
                    return failedResult;
                break;
            case YEAR:
                if(period.getPeriodNumber() != 0)
                    return failedResult;
                break;
        }
        return Collections.emptyList();
    }

    public static List<ValidationResult> validateYear(TypedPeriod period){
       if( period.getYear() < 1900 && period.getYear() > 2020)
           return Collections.singletonList(new ValidationResult(YEAR, YEAR + ".invalid"));
       return Collections.emptyList();
    }

    public String asString(){
       return type.getCode() + "." + DateUtils.pad2digits(periodNumber) + "." + year;
    }

    public static List<ValidationResult> validate(TypedPeriod typedPeriod){
        return CompositeValidator.validate(typedPeriod, Arrays.asList(
                TypedPeriod::validatePeriod,
                TypedPeriod::validateType,
                TypedPeriod::validateYear));
    }
}
