package ast;

import java.util.*;

public class Block extends Ast {
    public List<Statement> statements;

    public Block(Position pos,
		 List<Statement> statements){
	this.pos = pos;
	this.statements = statements;
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }    
}

