package ru.ibs.gisgmp.common.validation;

import java.util.List;

public class CorrectIfAnyValidator<T> implements Validator<T>{
    private List<Validator<T>> validators;

    public CorrectIfAnyValidator(List<Validator<T>> validators){
        this.validators = validators;
    }

    @SafeVarargs
    public CorrectIfAnyValidator(Validator<T>... validators){
//        this(Arrays.stream(validators).collect(Collectors.toList()));
        this((List)null);
    }


    @Override
    public List<ValidationResult> validate(T obj) {
        return validate(obj, validators);
    }

    public static<T> List<ValidationResult> validate(T obj, List<Validator<T>> validators){
//        if(validators.stream().anyMatch(val -> val.validate(obj).isEmpty()))
//            return Collections.emptyList();
//
//        return validators.stream().map(val -> val.validate(obj)).
//                flatMap(List::stream).collect(Collectors.toList());
        return null;
    }

    @SafeVarargs
    public static<T> List<ValidationResult> validate(T obj, Validator<T>... validators){
//       return validate(obj, Arrays.stream(validators).collect(Collectors.toList()));
        return null;
    }
}
