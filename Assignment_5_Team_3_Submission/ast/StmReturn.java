package ast;

public class StmReturn extends Statement {
    public Expression exp;
    public StmReturn(Position pos, Expression exp){
	this.pos = pos;
	this.exp = exp;
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
