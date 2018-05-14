package ru.ibs.gisgmp.charge;

public enum Meaning {
    NEW(1, "Новое"), AMEND(2, "Уточнение"), ANNULATE(3, "Уточнение об аннулировании"), DEANNULATE(4, "Уточнение при восстановлении");

    private String name;
    private int code;

    Meaning(int code, String name){
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
