package ast;

import java.util.*;

public class StmExp extends Statement {
    public Position pos;
    public Expression exp;
    
    public StmExp (Position pos, Expression exp)
    {
	this.pos = pos;
	this.exp = exp;
	
    }
    
    public <T> T accept(Visitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
