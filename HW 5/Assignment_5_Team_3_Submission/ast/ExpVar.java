package ast;

public class ExpVar extends Expression {
    public String name;
    public ExpVar(Position pos, String name){
	this.pos = pos;
	this.name = name;
    }
    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }    
}
