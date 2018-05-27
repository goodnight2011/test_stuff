package ru.ibs.nsi.validation;

import java.util.Collections;
import java.util.List;

public class RegExpValidator implements Validator<String>{

    private final String regexp;
    private String path;
    private String errCode;

    public RegExpValidator(String regexp, String path, String errCode) {
        if(regexp == null || regexp.trim().isEmpty())
            throw new IllegalArgumentException("regexp shouldn't be empty");

        if(path == null)
            throw new IllegalArgumentException("path shouldn't be empty");

        if(errCode == null || errCode.trim().isEmpty())
            throw new IllegalArgumentException("errCode shouldn't be empty");

        this.regexp = regexp;
        this.path = path;
        this.errCode = errCode;
    }

    @Override
    public List<ValidationResult> validate(String obj) {
        return validate(obj, regexp, path, errCode);
    }

    public static List<ValidationResult> validate(String text, String regexp, String path, String errCode){
        if(text.matches(regexp))
            Collections.singletonList(new ValidationResult(path, errCode));

        return Collections.emptyList();
    }
}
