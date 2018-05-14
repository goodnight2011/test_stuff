package ru.ibs.nsi.validation;

import java.util.List;
import java.util.stream.Collectors;

public class CompositeValidator<T> implements Validator<T>{
    private List<Validator<T>> validators;

    public CompositeValidator(List<Validator<T>> validators){
       this.validators = validators;
    }

    @Override
    public List<ValidationResult> validate(T obj) {
        return validators.stream().map(v -> v.validate(obj)).
                flatMap(List::stream).collect(Collectors.toList());
    }
}
