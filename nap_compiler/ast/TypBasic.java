package ast;

public class TypBasic extends Type {
    
    public BasicType type;

    public TypBasic(BasicType type){
	this.type = type;
    }

    public <T> T accept(Visitor<T> visitor){
	return visitor.visit(this);
    }
    
}

