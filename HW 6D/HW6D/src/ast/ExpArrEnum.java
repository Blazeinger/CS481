package ast;

import java.util.*;

public class ExpArrEnum extends Expression {
    public List<Expression> exps;

    public ExpArrEnum(Position pos,
		      List<Expression> exps)
    {
	this.pos = pos;
	this.exps = exps;
    }

    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
