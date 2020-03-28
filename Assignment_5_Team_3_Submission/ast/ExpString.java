package ast;

public class ExpString extends Expression {
    public String value;
    public ExpString(Position pos, String value){
		this.pos = pos;
		this.value = value;
    }
    public <T> T accept(Visitor<T> visitor){
		return visitor.visit(this);
    }
}
