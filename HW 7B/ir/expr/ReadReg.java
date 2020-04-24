package ir.expr;

import ir.Type;

public class ReadReg extends Expression
{
    private ir.Register reg;

    @Override
    public Type getType() {
        return reg.getType();
    }

    public ReadReg(ir.Register reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return reg.toString();
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
