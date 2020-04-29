package ast;

import java.util.*;

public class StmAssign extends Statement {
    public Expression lValue;
    public Expression exp;
    public Optional<OpBinary> op;

    public StmAssign(Position pos, Expression lValue, Expression exp) {
        this.pos = pos;
        this.lValue = lValue;
        this.exp = exp;
        this.op = Optional.empty();
    }

    public StmAssign(Position pos, Expression lValue, Expression exp, OpBinary op) {
        assert (op == OpBinary.ADD || op == OpBinary.SUB ||
                op == OpBinary.MUL || op == OpBinary.DIV);
        this.pos = pos;
        this.lValue = lValue;
        this.exp = exp;
        this.op = Optional.of(op);
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
