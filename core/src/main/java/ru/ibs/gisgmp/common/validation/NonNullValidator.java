package ru.ibs.gisgmp.common.validation;

import java.util.Collections;
import java.util.List;

public class NonNullValidator<T> implements Validator<T> {
    private String path;
    private String errCode;

    public NonNullValidator(String path, String errCode) {
        this.path = path;
        this.errCode = errCode;
    }

    @Override
    public List<ValidationResult> validate(T obj) {
        return validate(obj, path, errCode);
    }

    public static<T> List<ValidationResult> validate(T obj, String path, String errCode){
        return obj != null ? Collections.emptyList() :
                Collections.singletonList(new ValidationResult(path, errCode));
    }

    public static<T> Validator<T> withNonNull(Validator<T> validator, String path, String errCode){
        NonNullValidator<T> nonNull = new NonNullValidator<>(path, errCode);
        return t -> {
            if(nonNull.validate(t).isEmpty())
                return validator.validate(t);
            else return nonNull.validate(t);
        };
    }

    public static<T> List<ValidationResult> validate(T obj, Validator<T> validator, String path, String errCode){
        return withNonNull(validator, path, errCode).validate(obj);
    }
}
