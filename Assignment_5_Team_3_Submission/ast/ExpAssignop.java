package ast;

public class ExpAssignop extends Expression
{
    public OpAssign op;
    public Expression exp;
    public boolean prefix;
    public ExpAssignop(Position pos, OpAssign op,
		       Expression e, boolean prefix)
    {
	this.pos = pos;
	this.op = op;
	this.exp = e;
	this.prefix = prefix;
    }
    public <T> T accept(Visitor<T> visitor)
    {
	return visitor.visit(this);
    }
}
