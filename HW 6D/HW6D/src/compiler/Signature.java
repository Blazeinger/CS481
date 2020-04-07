package compiler;

import type.Type;

import java.util.*;

import util.*;

public class Signature {
    public List<Pair<Type, Boolean>> argTypes; // Different from W2
    public Optional<Type> returnType;          // Different from W2

    // Different from W2
    private static List<Pair<Type, Boolean>> passByValue(List<Type> argTypes) {
        List<Pair<Type, Boolean>> argTypePbRefs = new LinkedList<>();
        for (Type type : argTypes)
            argTypePbRefs.add(new Pair<>(type, false));
        return argTypePbRefs;
    }

    // Different from W2
    public Signature(List<Pair<Type, Boolean>> argTypes, Optional<Type> returnType) {
        this.argTypes = argTypes;
        this.returnType = returnType;
    }

    // Different from W2
    private Signature(List<Type> argTypes, Type returnType) {
        this(passByValue(argTypes), Optional.of(returnType));
    }

    public static Signature buildBinary(Type t1, Type t2, Type rt) {
        List<Type> argTypes = new ArrayList<>();
        argTypes.add(t1);
        argTypes.add(t2);
        return new Signature(argTypes, rt);
    }

    public static Signature buildUnary(Type type, Type rt) {
        List<Type> argTypes = new ArrayList<>();
        argTypes.add(type);
        return new Signature(argTypes, rt);
    }

    public final static Signature binaryArithmetic =
            buildBinary(type.Basic.INT, type.Basic.INT, type.Basic.INT);
    public final static Signature binaryBoolean =
            buildBinary(type.Basic.BOOL, type.Basic.BOOL, type.Basic.BOOL);
    public final static Signature unaryArithmetic =
            buildUnary(type.Basic.INT, type.Basic.INT);
    public final static Signature unaryBoolean =
            buildUnary(type.Basic.BOOL, type.Basic.BOOL);
    public final static Signature comparison =
            buildBinary(type.Basic.INT, type.Basic.INT, type.Basic.BOOL);

    public boolean check(List<Type> types) {
        if (types.size() == argTypes.size()) {
            for (int counter = 0; counter < types.size(); counter++)
                // Different from W2: getFst()
                if (!types.get(counter).equals(argTypes.get(counter).getFst()))
                    return false;
            return true;
        }
        return false;
    }

    public boolean check(Type type) {
        List<Type> types = new ArrayList<>();
        types.add(type);
        return check(types);
    }

    public boolean check(Type t1, Type t2) {
        List<Type> types = new ArrayList<>();
        types.add(t1);
        types.add(t2);
        return check(types);
    }
}
