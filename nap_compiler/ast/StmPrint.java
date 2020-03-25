package ast;

public class StmPrint extends Statement {
    public TypBasic type;
    public Expression exp;

    public StmPrint(Position pos,
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
