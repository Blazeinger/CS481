package ast;

import java.util.List;

public class ExpPredefinedCall extends Expression {
    public OpPredefined funcName;
    public List<Expression> arguments;

    public ExpPredefinedCall(Position pos, OpPredefined funcName,
                             List<Expression> arguments) {
        this.pos = pos;
        this.funcName = funcName;
        this.arguments = arguments;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
