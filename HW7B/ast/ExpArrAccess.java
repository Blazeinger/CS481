package ast;

import java.util.*;

public class ExpArrAccess extends Expression {
    public Expression array;
    public Expression index;
    
    public ExpArrAccess ( Position pos,
			       Expression array,
			       Expression index )
    {
	this.pos = pos;
	this.array = array;
	this.index = index;
    }
    
    public <T> T accept(Visitor<T> visitor)
    {
        return visitor.visit(this);
    }
	

}
