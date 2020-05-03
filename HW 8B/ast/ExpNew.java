package ast;

public class ExpNew extends Expression {
    public Type type;
    public Expression exp;

    public ExpNew(Position pos,
                  Type type,
                  Expression exp) {
        this.pos = pos;
        this.type = type;
        this.exp = exp;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
