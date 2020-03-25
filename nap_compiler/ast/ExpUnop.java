package ast;

public class ExpUnop extends Expression{
    public Expression exp;
    public OpUnary op;
    
    public ExpUnop(Position pos, Expression exp, OpUnary op )
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
