package ru.ibs.gisgmp.charge.requisites.period;

public enum PeriodType {

    MONTH("МС"), QUARTER("КВ"), HALF("ПЛ"), YEAR("ГД");

    private PeriodType(String code){
        this.code  = code;
    }

    private String code;

    public String getCode(){
        return code;
    }
}
