package ast;

public class ExpInt extends Expression {
    public int value;
    public ExpInt(Position pos, int value){
	this.pos = pos;
	this.value = value;
    }
    public <T> T accept(Visitor<T> visitor){
		return visitor.visit(this);
    }
}
