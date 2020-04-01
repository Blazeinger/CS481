package compiler;
import ast.OpBinary;
import ast.OpUnary;
import java.util.*;

public class Signatures
{
    public static final Map<OpBinary, Signature> binary = buildBinary();
    public static final Map<OpUnary, Signature> unary = buildUnary();
    private static Map<OpBinary, Signature> buildBinary(){
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
    private static Map<OpUnary, Signature> buildUnary(){
	Map<OpUnary, Signature> mapping = new HashMap<>();
	mapping.put(OpUnary.MINUS, Signature.unaryArithmetic);
 	mapping.put(OpUnary.NOT, Signature.unaryBoolean);
	mapping.put(OpUnary.INCR, Signature.comparison);
	mapping.put(OpUnary.DECR, Signature.comparison);
	return mapping;
    }
}
