package ru.ibs.gisgmp.common.validation;

import java.util.List;

public class CompositeValidator<T> implements Validator<T>{
    private List<Validator<T>> validators;

    public CompositeValidator(List<Validator<T>> validators){
       this.validators = validators;
    }

    @SafeVarargs
    public CompositeValidator(Validator<T>... validators){






































































































































































































































































































































































































































































































































































































        //        this(Arrays.stream(validators).collect(Collectors.toList()));
        this((List)null);
    }


    @Override
    public List<ValidationResult> validate(T obj) {
        return null;
//        return validators.stream().map(v -> v.validate(obj)).
//                flatMap(List::stream).collect(Collectors.toList());
    }

    public static<T> List<ValidationResult> validate(T obj, List<Validator<T>> validators){
//        return validators.stream().map(v -> v.validate(obj)).
//                flatMap(List::stream).collect(Collectors.toList());
        return null;
    }

    @SafeVarargs
    public static<T> List<ValidationResult> validate(T obj, Validator<T>... validators){
//        return validate(obj, Arrays.stream(validators).collect(Collectors.toList()));
        return null;
    }
}