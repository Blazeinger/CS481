package ast;

import java.util.*;

public class StmIf extends Statement
{
    public Expression condition;
    public Block then_branch;
    public Optional<Block> else_branch;

    public StmIf(Position pos,
		 Expression condition,
		 Block then_branch){
	this.pos = pos;
    	this.condition = condition;
	this.then_branch = then_branch;
	this.else_branch = Optional.empty();
    }
    public StmIf(Position pos,
		 Expression condition,
		 Block then_branch, Block else_branch){
	this.pos = pos;
	this.condition = condition;
	this.then_branch = then_branch;
	this.else_branch = Optional.of(else_branch);
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
