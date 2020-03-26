package ast;

import java.util.*;

public class Program extends Ast {
    public List<FunctionDefinition> functions;

    public Program(Position pos,
		   List<FunctionDefinition> functions){
	this.pos = pos;
	this.functions = functions;
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }    
}
