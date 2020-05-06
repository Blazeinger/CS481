package ir.expr;


import ast.OpUnary;
import ir.Type;

public class Unary extends Expression
{

    private Expression exp;
    private OpUnary op;

    @Override
    public Type getType() {
        switch(op){
            case SUB: return Type.INT;
            default: return Type.BYTE;
        }
    }

    public Expression getExp() {
        return exp;
    }

    public OpUnary getOp() {
        return op;
    }

    public Unary(Expression exp, OpUnary op) {
        this.exp = exp;
        this.op = op;
    }

    @Override
    public String toString() {
        return op + "(" + exp + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
