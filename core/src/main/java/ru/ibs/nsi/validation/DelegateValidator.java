package ru.ibs.nsi.validation;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DelegateValidator<T, M> implements Validator<T>{
    private String pathPrefix;
    private Validator<M> validator;
    private Function<T, M> converter;

    public DelegateValidator(String pathPrefix, Validator<M> validator, Function<T, M> converter) {
        this.pathPrefix = pathPrefix;
        this.validator = validator;
        this.converter = converter;
    }

    @Override
    public List<ValidationResult> validate(T obj) {
        return validate(obj, pathPrefix, validator, converter);
    }

    private static ValidationResult convertResult(ValidationResult res, String pathPrefix){
        return new ValidationResult(pathPrefix + (res.getPath().isEmpty() ? "" : "." + res.getPath()),
                res.getErrCode());
    }

    public static<T, M> List<ValidationResult> validate(T obj, String pathPrefix, Validator<M> validator, Function<T, M> converter){
        return validator.validate(converter.apply(obj)).stream().
                map(res -> convertResult(res, pathPrefix)).collect(Collectors.toList());
    }
}
