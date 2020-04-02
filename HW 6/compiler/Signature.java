package compiler;

import util.Pair;
import type.*;

import java.util.*;

public class Signature {
    public List<Pair<Pair<String, Type>, Boolean>> argTypes;
    public type.Type returnType;

    private Signature(List<Pair<Pair<String, Type>, Boolean>> argTypes,
		      type.Type returnType){
	this.argTypes = argTypes;
	this.returnType = returnType;
    }

    private static Signature buildBinary(type.Type t1, type.Type t2, type.Type rt){
	List<Pair<Pair<String, Type>, Boolean>> argTypes = new ArrayList<Pair<Pair<String, Type>, Boolean>>();
	Pair<String, Type> tempPair = new Pair<String, Type>(null, t1);
	argTypes.add(new Pair<Pair<String, Type>, Boolean>(tempPair, false));
	tempPair = new Pair<String, Type>(null, t2);
	argTypes.add(new Pair<Pair<String, Type>, Boolean>(tempPair, false));
	return new Signature(argTypes, rt);
    }

    private static Signature buildUnary(type.Type type, type.Type rt){
	List<Pair<Pair<String, Type>, Boolean>> argTypes = new ArrayList<Pair<Pair<String, Type>, Boolean>>();
	Pair<String, Type> tempPair = new Pair<String, Type>(null, type);
	argTypes.add(new Pair<Pair<String, Type>, Boolean>(tempPair, false));
	return new Signature(argTypes, rt);
    }

    public static Signature buildFunction(List<Pair<Pair<String, Type>, Boolean>> args, type.Type rt){
	    return new Signature(args, rt);
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

	// TODO: create "print", "read", and type conversions

    private boolean check(ArrayList<type.Type> types){
	if (types.size() == argTypes.size()){
	    for(int counter = 0; counter < types.size(); counter++)
		if (!types.get(counter).equals(argTypes.get(counter)))
		    return false;
	    return true;
	}
	return false;
    }

    public boolean check(type.Type type){
	ArrayList<type.Type> types = new ArrayList<>();
	types.add(type);
	return check(types);
    }

    public boolean check(type.Type t1, type.Type t2){
	ArrayList<type.Type> types = new ArrayList<>();
	types.add(t1);
	types.add(t2);
	return check(types);
    }

	// TODO: check method for functions
	public boolean check(){
		return false;
	}
}
