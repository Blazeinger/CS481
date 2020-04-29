package ast;
import java.util.*;
public class StmRead extends Statement {
    public Type type;
    public Expression exp;
    public StmRead(Position pos, Type type, Expression exp){
		this.pos = pos;
		this.type = type;
		this.exp = exp;
    }
    public <T> T accept(Visitor<T> visitor){
		return visitor.visit(this);
    }
}
