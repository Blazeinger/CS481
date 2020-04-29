package ast;

public class ExpChar extends Expression {
    public char value;
    public ExpChar(Position pos, char value){
		this.pos = pos;
		this.value = value;
    }
    public <T> T accept(Visitor<T> visitor){
		return visitor.visit(this);
    }
}
