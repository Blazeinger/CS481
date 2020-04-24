package ir.com;

import ir.expr.Expression;
import ir.Type;

public class WriteMem extends Command
{
    private Expression leftValue;
    private Expression exp;
    private Type type;

    public Expression getLeftValue() {
        return leftValue;
    }

    public Expression getExp() {
        return exp;
    }

    public WriteMem(Expression lValue, Expression exp, Type type) {
        this.leftValue = lValue;
        this.exp = exp;
        this.type = type;
    }

    @Override
    public String toString() {
        return leftValue + " : " + type + " := " + exp;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
