package compiler;

import ast.OpBinary;
import ast.OpUnary;
import ast.OpPredefined;
import type.Basic;

import java.util.*;

public class Signatures {
    public static final Map<OpBinary, Signature> binary = buildBinary();
    public static final Map<OpUnary, Signature> unary = buildUnary();
    public static final Map<OpPredefined, Signature> predefined = buildPredefined();

    private static Map<OpBinary, Signature> buildBinary() {
        Map<OpBinary, Signature> mapping = new HashMap<>();
        mapping.put(OpBinary.ADD, Signature.binaryArithmetic);
        mapping.put(OpBinary.SUB, Signature.binaryArithmetic);
        mapping.put(OpBinary.MUL, Signature.binaryArithmetic);
        mapping.put(OpBinary.DIV, Signature.binaryArithmetic);
        mapping.put(OpBinary.MOD, Signature.binaryArithmetic);
        mapping.put(OpBinary.AND, Signature.binaryBoolean);
        mapping.put(OpBinary.OR, Signature.binaryBoolean);
        mapping.put(OpBinary.LT, Signature.comparison);
        mapping.put(OpBinary.LE, Signature.comparison);
        mapping.put(OpBinary.GT, Signature.comparison);
        mapping.put(OpBinary.GE, Signature.comparison);
        // EQ and NEQ not there: they'd need a polymorphic signature
        return mapping;
    }

    private static Map<OpUnary, Signature> buildUnary() {
        Map<OpUnary, Signature> mapping = new HashMap<>();
        mapping.put(OpUnary.SUB, Signature.unaryArithmetic);
        mapping.put(OpUnary.NOT, Signature.unaryBoolean);
        return mapping;
    }

    private static Map<OpPredefined, Signature> buildPredefined() {
        Map<OpPredefined, Signature> mapping = new HashMap<>();
        mapping.put(OpPredefined.BYTE_OF_CHAR, Signature.buildUnary(Basic.CHAR, Basic.BYTE));
        mapping.put(OpPredefined.BYTE_OF_INT, Signature.buildUnary(Basic.INT, Basic.BYTE));
        mapping.put(OpPredefined.CHAR_OF_BYTE, Signature.buildUnary(Basic.BYTE, Basic.CHAR));
        mapping.put(OpPredefined.INT_OF_BYTE, Signature.buildUnary(Basic.BYTE, Basic.INT));
        // No signature for LENGTH because it is polymorphic
        return mapping;
    }
}
