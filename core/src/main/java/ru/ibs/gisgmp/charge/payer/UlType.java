package ru.ibs.gisgmp.charge.payer;

public enum UlType {
    UL("ul","ЮЛ"), IP("ip","ИП"), FL("fl","ФЛ");

    private final String code;
    private final String name;

    UlType(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
