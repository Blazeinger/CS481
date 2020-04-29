package ast;

import util.Pair;
import java.util.*;

public class FunctionDefinition extends Ast {
    public String name;
    public List<Pair<Pair<String, Type>, Boolean>> arguments;
    public Optional<Type> returnType;
    public Block body;
    // ...
    public FunctionDefinition(Position pos, String name, 
	     List<Pair<Pair<String, Type>, Boolean>> arguments,
	     Block body){
	this.pos = pos;
	this.name = name;
	this.arguments = arguments;
	this.body = body;
	this.returnType = Optional.empty();
    }
    public FunctionDefinition(Position pos, String name,
	     List<Pair<Pair<String, Type>, Boolean>> arguments,
	     Block body, Type returnType){
	this.pos = pos;
	this.name = name;
	this.arguments = arguments;
	this.body = body;
	this.returnType = Optional.of(returnType);
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
