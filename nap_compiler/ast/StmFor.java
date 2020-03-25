package ast;

public class StmFor extends Statement{
    public Type type;
    public String var;
    public Expression collection;
    public Block body;
    
    public StmFor( Position pos, Type type,
		   String identifier, Expression expr,
		   Block block )
    {
	this.type = type;
	this.var = var;
	this.collection = expr;
	this.body = block;
	this.pos = pos;
    }
    
    public <T> T accept(Visitor<T> visitor)
    {
	return visitor.visit(this);
    }
}
