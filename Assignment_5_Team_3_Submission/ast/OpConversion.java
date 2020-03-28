package ast;

public enum OpConversion {
    BYTE_OF_INT { public String toString() { return "byte_of_int"; } },
    INT_OF_BYTE { public String toString() { return "int_of_byte"; } },
    CHAR_OF_BYTE { public String toString() { return "char_of_byte"; } },
    BYTE_OF_CHAR { public String toString() { return "byte_of_char"; } },
}
