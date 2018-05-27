package ru.ibs.common;

import ru.ibs.nsi.validation.ValidationResult;

import java.util.List;

public abstract class StringBased {
    private String string;

//    private void check(String string){
//        if (!validate(string).isEmpty())
//            throw new IllegalArgumentException("Incorrect value: " + string
//                    + " for class: " + getClass().getCanonicalName());
//    }

    public void setString(String string){
//        check(string);
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
