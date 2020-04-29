package ast;

import java.util.*;

public class ExpFuncCall extends Expression
{
    public String funcName;
    public List<Expression> arguments;

    public ExpFuncCall(Position pos, String funcName,
		       List<Expression> arguments)
    {
        this.pos = pos;
        this.funcName = funcName;
        this.arguments = arguments;
    }

    public <T> T accept(Visitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
