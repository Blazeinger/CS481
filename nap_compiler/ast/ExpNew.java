package ast;

public class ExpNew extends Expression {
    public TypBasic type;
    public Expression exp;

    public ExpNew(Position pos,
                  TypBasic type,
                  Expression exp) {
        this.pos = pos;
        this.type = type;
        this.exp = exp;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
