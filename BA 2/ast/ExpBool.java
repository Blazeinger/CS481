package ast;

public class ExpBool extends Expression {
    public boolean value;
    public ExpBool(Position pos, boolean value){
		this.pos = pos;
		this.value = value;
    }
    public <T> T accept(Visitor<T> visitor){
		return visitor.visit(this);
    }
}
