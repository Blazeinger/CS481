package ir.expr;


import ir.Type;

public class Unary extends Expression
{
    public static enum Operation{
        SUB,
        NOT,
        LENGTH,
        CHAR_OF_BYTE,
        BYTE_OF_CHAR,
        INT_OF_BYTE,
        BYTE_OF_INT
    }

    private Expression exp;
    private Operation op;

    @Override
    public Type getType() {
        switch(op){
            case SUB: case LENGTH: case INT_OF_BYTE: return Type.INT;
            default: return Type.BYTE;
        }
    }

    public Expression getExp() {
        return exp;
    }

    public Operation getOp() {
        return op;
    }

    public Unary(Expression exp, Operation op) {
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
