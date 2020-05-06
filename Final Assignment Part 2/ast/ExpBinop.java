package ast;

public class ExpBinop extends Expression {
    public Expression left;
    public Expression right;
    public OpBinary op;
    public ExpBinop(Position pos, Expression left,
		    OpBinary op, Expression right){
	this.pos = pos;
	this.left = left;
	this.op = op;
	this.right = right;
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
