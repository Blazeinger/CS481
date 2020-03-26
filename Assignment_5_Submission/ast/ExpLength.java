package ast;

public class ExpLength extends Expression
{
    public Expression exp;
    
    public ExpLength(Position pos, Expression exp)
    {
        this.pos = pos;
        this.exp = exp;
    }
    
    public <T> T accept(Visitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
