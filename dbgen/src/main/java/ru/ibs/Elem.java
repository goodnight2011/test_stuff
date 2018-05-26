package ru.ibs;

class Elem {
    private final String name;
    private final ElemType type;

    public Elem(String name, ElemType type) {
        if(DbGenMojo.emptyStr(name))
            throw new IllegalArgumentException("name of element can't be empty");

        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ElemType getType() {
        return type;
    }

    public boolean isCatalog(){
       return type.equals(ElemType.CAT);
    }

    public boolean isSchema(){
        return type.equals(ElemType.SHM);
    }

    public boolean isDefault(){
        return name.equals(DbGenMojo.DFLT);
    }

    public boolean isTable(){
        return type.equals(ElemType.TB);
    }

    public boolean isSequence(){
        return type.equals(ElemType.SEQ);
    }
}
