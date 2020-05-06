package ast;

public class StmPrint extends Statement {
    public Type type;
    public Expression exp;

    public StmPrint(Position pos,
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
