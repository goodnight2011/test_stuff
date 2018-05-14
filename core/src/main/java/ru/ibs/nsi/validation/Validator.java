package ru.ibs.nsi.validation;

import java.util.List;

public interface Validator<T> {
    List<ValidationResult> validate(T obj);
}
