package ast;

public enum BasicType {
    INT   { public String toString() { return "int"; } },
    BOOL  { public String toString() { return "bool"; } },
    BYTE  { public String toString() { return "byte"; } },
    FLOAT { public String toString() { return "float"; } },
    CHAR  { public String toString() { return "char"; } },
}

