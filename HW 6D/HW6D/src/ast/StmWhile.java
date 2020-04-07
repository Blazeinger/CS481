package ast;

public class StmWhile extends Statement
{
    public Block body;
    public Expression condition;
    public boolean doWhile;
    // Two static factories: one for while loops, one for do while loops
    public static StmWhile While(Position pos,
				 Expression expr, Block block)
    {
	return new StmWhile(pos, expr, block, false);
    }
    public static StmWhile DoWhile(Position pos,
				   Expression expr, Block block)
    {
	return new StmWhile(pos, expr, block, true);
    }

    private StmWhile(Position pos,
		     Expression expr, Block block, boolean doWhile )
    {
	this.pos = pos;
	this.body = block;
	this.condition = expr;
	this.doWhile = doWhile;
    }
    
    public <T> T accept(Visitor<T> visitor)
    {
	return visitor.visit(this);
    }
}
