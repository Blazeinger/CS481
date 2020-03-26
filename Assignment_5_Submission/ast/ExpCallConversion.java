package ast;

public class ExpCallConversion extends Expression {
    public OpConversion con;
    public Expression exp;
    
    public ExpCallConversion (Position pos,
			      OpConversion con,
			      Expression exp)
    {
	this.pos = pos;
	this.con = con;
	this.exp = exp;
    }
    
    public <T> T accept(Visitor<T> visitor) {
	return visitor.visit(this);
    }
}
