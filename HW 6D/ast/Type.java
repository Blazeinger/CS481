package ast;

public class Type extends Ast
{
    public type.Type type;

    public Type(Position pos, type.Type type) {
        this.pos = pos;
        this.type = type;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
