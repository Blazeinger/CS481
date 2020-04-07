package compiler;

import java.util.*;

public class ReservedWord {
    private static final TreeSet<String> reserved = buildReserved();

    private static TreeSet<String> buildReserved() {
        String[] reservedWords = {
                // "for", "do", "while", "read", "print", "func", "var",
                // "array", "int", "bool", "char", "byte"
                "char_of_byte", "byte_of_char", "int_of_byte", "byte_of_int", "length"};
        TreeSet<String> reserved = new TreeSet<>();
        for (String word : reservedWords)
            reserved.add(word);
        return reserved;
    }

    public static boolean is(String name) {
        return reserved.contains(name);
    }
}
