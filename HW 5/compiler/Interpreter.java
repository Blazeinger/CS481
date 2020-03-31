package compiler;

import ast.*;

import java.util.*;
import java.io.*;

public class Interpreter implements Visitor<Value> {
    private Map<String, Value> environment;
    private Map<String, TypBasic> typeEnvironment;
    private BufferedReader in =
	new BufferedReader(new InputStreamReader(System.in));
    // ...
    @Override
    public Value visit(ExpUnop exp) {
	switch (exp.op){
	case MINUS:
	    // We assume here that the AST is well typed
	    // for e.g. here, exp.exp is supposed to be
	    // an integer, hence the cast is valid.
	    int n = ((Value.Int) exp.exp.accept(this)).value;
	    return new Value.Int(-n);
	case NOT:
	    boolean b = ((Value.Bool) exp.exp.accept(this)).value;
	    return new Value.Bool(!b);
	}
	return Value.none;
    }

    @Override
    public Value visit(ExpBinop exp) {
	Value v1 = exp.left.accept(this);
	Value v2 = exp.right.accept(this);
	switch(exp.op){
	case ADD:
	    return new Value.Int(((Value.Int)v1).value + ((Value.Int)v2).value);
	case MUL:
	    return new Value.Int(((Value.Int)v1).value * ((Value.Int)v2).value);
	case SUB:
	    return new Value.Int(((Value.Int)v1).value - ((Value.Int)v2).value);
	case DIV:
	    return new Value.Int(((Value.Int)v1).value / ((Value.Int)v2).value);
	case MOD:
	    return new Value.Int(((Value.Int)v1).value % ((Value.Int)v2).value);
	case LT:
	    return new Value.Bool(((Value.Int)v1).value < ((Value.Int)v2).value);
	case LE:
	    return new Value.Bool(((Value.Int)v1).value <= ((Value.Int)v2).value);
	case GT:
	    return new Value.Bool(((Value.Int)v1).value > ((Value.Int)v2).value);
	case GE:
	    return new Value.Bool(((Value.Int)v1).value >= ((Value.Int)v2).value);
	case AND:
	    return new Value.Bool(((Value.Bool)v1).value&&((Value.Bool)v2).value);
	case OR:
	    return new Value.Bool(((Value.Bool)v1).value||((Value.Bool)v2).value);
	case EQ:
	    return new Value.Bool(v1.equals(v2));
	case NEQ:
	    return new Value.Bool(!v1.equals(v2));
	}
	return Value.none;
    }

    @Override
    public Value visit(ExpInt num) {
        return new Value.Int(num.value);
    }

    @Override
    public Value visit(ExpBool bool) {
        return new Value.Bool(bool.value);
    }

    @Override
    public Value visit(ExpVar var) {
        return environment.get(var.name);
    }

    @Override
    public Value visit(InsInput ins) {
	String input = "";
	Value value = Value.none;
	TypBasic type = typeEnvironment.get(ins.var);
	System.out.print(ins.var + "? ");
	try {
	    input  = in.readLine();
	} catch (IOException e) {
            System.out.println(e);
	    System.exit(-1);
        }
	switch (type){
	case INTEGER:
	    value = new Value.Int(Integer.parseInt(input));
	    break;
	case BOOLEAN:
	    value = new Value.Bool(Boolean.parseBoolean(input));
	}
	environment.put(ins.var, value);
	return Value.none;
    }

    @Override
    public Value visit(InsPrint ins) {
	Value v = ins.exp.accept(this);
	if (v.isBool())
	    System.out.println(((Value.Bool)v).value);
	if (v.isInt())
	    System.out.println(((Value.Int)v).value);
        return Value.none;
    }

    @Override
    public Value visit(Typ type) {
        return new Value.Type(type.type);
    }

    @Override
    public Value visit(InsIf ins) {
        Value.Bool condition = (Value.Bool) ins.exp.accept(this);
	if (condition.value)
	    return ins.then_branch.accept(this);
	else
	    return ins.else_branch.accept(this);
    }

    @Override
    public Value visit(InsWhile ins) {
	while ((ins.exp.accept(this)).value) {
	    ins.body.accept(this);
	}
	return Value.none;
    }

    @Override
    public Value visit(InsAssign ins) {
        Value value = ins.exp.accept(this);
        String var = ins.var;
	environment.put(var, value);
	return Value.none;
    }

    @Override
    public Value visit(Block block) {
        for (Ins ins : block.instructions)
	    ins.accept(this);
        return Value.none;
    }

    @Override
    public Value visit(Declaration declaration) {
	// Could be removed if built by a
	// first phase of semantic analysis
        TypBasic type = declaration.type.type;
	for(String var : declaration.vars)
	    typeEnvironment.put(var, type);
	return Value.none;
    }

    @Override
    public Value visit(Program program) {
        for(Declaration d : program.declarations)
            d.accept(this);
        return program.main.accept(this);
    }

    public Interpreter(){
	typeEnvironment =
	    new TreeMap<String, TypBasic>();
	environment =
	    new TreeMap<String, Value>();
    }
}
