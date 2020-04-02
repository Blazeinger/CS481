package type;

public class Basic extends type.Type {
    // An enumeration cannot extends an abstrac class
    // The choice here is not have the enumeration as
    // a private inner class.
    private enum BasicType {
        INT   { public String toString() { return "int"; } },
        BOOL  { public String toString() { return "bool"; } },
        BYTE  { public String toString() { return "byte"; } },
        FLOAT { public String toString() { return "float"; } },
        CHAR  { public String toString() { return "char"; } },
    }

    private BasicType type;
    private Basic(BasicType type){
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
    public static final Basic INT = new Basic(BasicType.INT);
    public static final Basic BOOL = new Basic(BasicType.BOOL);
    public static final Basic BYTE = new Basic(BasicType.BYTE);
    public static final Basic FLOAT = new Basic(BasicType.FLOAT);
    public static final Basic CHAR = new Basic(BasicType.CHAR);
}



