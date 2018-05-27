package ru.ibs.nsi.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeValidator<T> implements Validator<T>{
    private List<Validator<T>> validators;

    public CompositeValidator(List<Validator<T>> validators){
       this.validators = validators;
    }

    @SafeVarargs
    public CompositeValidator(Validator<T>... validators){
        this(Arrays.stream(validators).collect(Collectors.toList()));
    }


    @Override
    public List<ValidationResult> validate(T obj) {
        return validators.stream().map(v -> v.validate(obj)).
                flatMap(List::stream).collect(Collectors.toList());
    }

    public static<T> List<ValidationResult> validate(T obj, List<Validator<T>> validators){
        return validators.stream().map(v -> v.validate(obj)).
                flatMap(List::stream).collect(Collectors.toList());
    }

    @SafeVarargs
    public static<T> List<ValidationResult> validate(T obj, Validator<T>... validators){
        return validate(obj, Arrays.stream(validators).collect(Collectors.toList()));
    }
}
