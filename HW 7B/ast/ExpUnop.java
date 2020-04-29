package ast;

public class ExpUnop extends Expression{
    public Expression exp;
    public OpUnary op;
    
    public ExpUnop(Position pos, OpUnary op, Expression exp)
    {
	this.pos = pos;
	this.exp = exp;
	this.op = op;
    }
    
    public <T> T accept(Visitor<T> visitor)
    {
	return visitor.visit(this);
    }
}
