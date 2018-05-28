package ru.ibs.gisgmp.common;

import ru.ibs.gisgmp.common.validation.ValidationResult;

import java.util.List;

public abstract class StringBased {
    private String string;

    public void setString(String string){
        this.string = string;
    }

    public List<ValidationResult> validate(){
        return validate(this.string);
    }

    protected abstract List<ValidationResult> validate(String str);

    public String getString(){
        return string;
    }

}
