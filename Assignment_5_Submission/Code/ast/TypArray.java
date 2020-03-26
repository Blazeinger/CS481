package ast;

public class TypArray extends Type
{
    public Type type;

    public TypArray(Type type){
	this.type = type;
    }

    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
}
