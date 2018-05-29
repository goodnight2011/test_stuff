package ru.ibs.gisgmp.common.validation;

import ru.ibs.gisgmp.common.utils.ArrUtils;

import java.util.ArrayList;
import java.util.List;

public class CorrectIfAnyValidator<T> implements Validator<T>{
    private List<Validator<T>> validators;

    public CorrectIfAnyValidator(List<Validator<T>> validators){
        this.validators = validators;
    }

    @SafeVarargs
    public CorrectIfAnyValidator(Validator<T>... validators){
        this(ArrUtils.arrToList(validators));
    }


    @Override
    public List<ValidationResult> validate(T obj) {
        return validate(obj, validators);
    }

    public static<T> List<ValidationResult> validate(T obj, List<Validator<T>> validators){
        if(ArrUtils.anyMatch(validators, val -> val.validate(obj).isEmpty()))
            return new ArrayList<>();

        return ArrUtils.concat(ArrUtils.map(validators, val -> val.validate(obj)));
    }

    @SafeVarargs
    public static<T> List<ValidationResult> validate(T obj, Validator<T>... validators){
        return validate(obj, ArrUtils.arrToList(validators));
    }
}
