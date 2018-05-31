package ru.ibs.gisgmp.charge.payer;

public enum ResidentType {
    RESIDENT(2, "Резидент РФ"), NOT_RESIDENT_WITH_INN(3, "Нерезидент РФ"), NOT_RESIDENT_WITH_KIO(3, "Нерезидент РФ(при наличии КИО)");

    private int code;
    private String name;

    ResidentType(int code, String name){
       this.code = code;
       this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
