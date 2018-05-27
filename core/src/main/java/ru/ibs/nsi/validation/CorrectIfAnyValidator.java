package ru.ibs.nsi.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CorrectIfAnyValidator<T> implements Validator<T>{
    private List<Validator<T>> validators;

    public CorrectIfAnyValidator(List<Validator<T>> validators){
        this.validators = validators;
    }

    @SafeVarargs
    public CorrectIfAnyValidator(Validator<T>... validators){
        this(Arrays.stream(validators).collect(Collectors.toList()));
    }


    @Override
    public List<ValidationResult> validate(T obj) {
        return validate(obj, validators);
    }

    public static<T> List<ValidationResult> validate(T obj, List<Validator<T>> validators){
        if(validators.stream().anyMatch(val -> val.validate(obj).isEmpty()))
            return Collections.emptyList();

        return validators.stream().map(val -> val.validate(obj)).
                flatMap(List::stream).collect(Collectors.toList());
    }

    @SafeVarargs
    public static<T> List<ValidationResult> validate(T obj, Validator<T>... validators){
       return validate(obj, Arrays.stream(validators).collect(Collectors.toList()));
    }
}
