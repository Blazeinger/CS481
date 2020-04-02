package ast;

import java.util.*;

public class StmAssign extends Statement {
    public Expression l_value;
    public Expression exp;
    public Optional<OpBinary> op;

    public StmAssign(Position pos, Expression l_value, Expression exp) {
        this.pos = pos;
        this.l_value = l_value;
        this.exp = exp;
        this.op = Optional.empty();
    }

    public StmAssign(Position pos, Expression l_value, Expression exp, OpBinary op) {
        assert (op == OpBinary.ADD || op == OpBinary.SUB ||
                op == OpBinary.MUL || op == OpBinary.DIV);
        this.pos = pos;
        this.l_value = l_value;
        this.exp = exp;
        this.op = Optional.of(op);
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
